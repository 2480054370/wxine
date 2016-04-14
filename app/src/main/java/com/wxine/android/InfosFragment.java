package com.wxine.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wxine.android.model.Comment;
import com.wxine.android.model.Image;
import com.wxine.android.model.Info;
import com.wxine.android.model.User;
import com.wxine.android.utils.InfoPage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private InfosAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading = false;
    private boolean isRefreshing = false;
    private int page = 0;
    ArrayList<Info> list = new ArrayList<Info>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfosFragment newInstance(String param1, String param2) {
        InfosFragment fragment = new InfosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public InfosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infos, container, false);

        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.info_refresh_widget);

        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.infos_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        datainit();



        mAdapter = new InfosAdapter(this.getContext(), list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InfosAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Info data) {
                Log.v("点击====", data.getTitle()+"here");
                Intent i = new Intent(getActivity(), InfoActivity.class);
                ImageView iview = (ImageView) view.findViewById(R.id.iv_agree_img);
                String tag = iview.getTag().toString();
                Toast.makeText(getActivity(),"aaa"+iview.getTag(),Toast.LENGTH_SHORT).show();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info", data);
                i.putExtra("IsComment",false);
                i.putExtra("tag",tag);
                i.putExtras(mBundle);
                startActivity(i);
            }

            public void onAgreeClick(ImageView view,int position){
                Toast.makeText(getActivity(),"aaa"+position,Toast.LENGTH_SHORT).show();
              //  view.setAlpha(0.0001f);
//                ImageView v = (ImageView) view.findViewById(R.id.iv_agree_img);
//                v.setImageResource(R.drawable.xreply);
               // view.setImageResource(R.drawable.favorite);
                if(view.getTag().toString().equals("no")){
                    view.setImageResource(R.drawable.favorite);
                    view.setTag("yes");
                }else {
                    view.setImageResource(R.drawable.xfavorite);
                    view.setTag("no");
                }
            }

            public  void onShareClick(View view,int position){
                showPopwindow();
            }

            public void onCommentClick(View view,Info data){
                Intent i = new Intent(getActivity(), InfoActivity.class);
                ImageView iview = (ImageView) view.findViewById(R.id.iv_agree_img);
                String tag = iview.getTag().toString();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info", data);
                i.putExtra("tag",tag);
                i.putExtras(mBundle);
                i.putExtra("IsComment",true);
                startActivity(i);
            }

//            public void onCommentClick(View view,int a){
//                Intent i = new Intent(getActivity(), CommentActivity.class);
//                startActivity(i);
//            }


        });
        new LoadDataTask().execute();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isRefreshing) {
                    Log.d("===", "ignore manually update!");
                } else {
                    new LoadDataTask().execute();
                }
            }
        });

        mRecyclerView.addOnScrollListener(new OnMyScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                // 到底部自动加载
                if (!isLoading){
                    Log.d("", "loading old data");
                    new LoadDataTask("load").execute();
                    isLoading = true;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //解决RecyclerView和SwipeRefreshLayout共用存在的bug
                //((LinearLayoutManager) mLayoutManager).findFirstCompletelyVisibleItemPosition()
                mRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        return view;
    }

    /**
     * 显示popupWindow
     */
    public void showPopwindow() {
        // 利用layoutInflater获得View
     //   LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     //   View view = inflater.inflate(R.layout.popwindowlayout, null);
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View view= inflater.inflate(R.layout.popwindowlayout,null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);



        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        WindowManager.LayoutParams lp = this.getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        this.getActivity().getWindow().setAttributes(lp);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(getActivity().findViewById(R.id.iv_share_img),
                Gravity.BOTTOM, 0, 0);

        RelativeLayout OtherShare  = (RelativeLayout) view.findViewById(R.id.OtherShareLayout);
        OtherShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                window.dismiss();
            }
        });
//        TextView OtherShare = (TextView) view.findViewById(R.id.OtherShareText);
//        OtherShare.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//            }
//        });

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }

    public void datainit(){
        // user
        User user = new User();
        user.setName("abc");
        user.setImage("http://img.teapic.com/thumbs/201305/28/101143gftcfpzwvukwqjsl.jpg.middle.jpg");
        user.setAddress("asgeq");

        //comment
        Comment comment = new Comment();
        comment.setContent("this is a comment1");
        comment.setUser(user);
        comment.setScore(60);
        comment.setId("1");
        comment.setIlike(1);
        comment.setCtime(new java.sql.Timestamp(2014121123));
        Comment comment3 = new Comment();
        comment3.setContent("this is a comment1");
        comment3.setUser(user);
        comment3.setScore(60);
        comment3.setId("1");
        comment3.setIlike(1);
        comment3.setCtime(new java.sql.Timestamp(2054121123));
        Comment ccomment = new Comment();
        ccomment.setContent("this is a comment1");
        ccomment.setUser(user);
        ccomment.setScore(60);
        ccomment.setId("1");
        ccomment.setIlike(1);
        ccomment.setCtime(new java.sql.Timestamp(2014121123));
        Set<Comment> set = new HashSet<>();
        set.add(comment);
        set.add(ccomment);
        set.add(comment3);
        Image img1 = new Image();
        img1.setUrl("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        Image img2 = new Image();
        img1.setUrl("http://img.teapic.com/thumbs/201305/28/101143gftcfpzwvukwqjsl.jpg.middle.jpg");
        Set<Image> img = new HashSet<>();
        img.add(img1);
        img.add(img2);
        Info info = new Info();
        info.setImages(img);
        info.setUser(user);
        info.setComment(comment);
        info.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info.setAddress("aaa");
        info.setBrief("qiuehgqlehg");
        info.setCmcount(12);
        info.setContent("this is content");
        info.setTitle("this is title");
        info.setName("abc");
        info.setComments(set);
        info.setCleancontent("this is c content1");

        //user
        User user2 = new User();
        user2.setName("aaa");
        user2.setImage("http://www.qqpk.cn/Article/UploadFiles/201112/20111228132051137.jpg");
        user2.setAddress("asgeq");
        //comment
        Comment comment2 = new Comment();
        comment2.setContent("this is a comment1");
        comment2.setUser(user2);
        comment2.setScore(60);
        comment2.setCtime(new java.sql.Timestamp(2014121123));
        Comment comment4 = new Comment();
        comment4.setContent("this is a comment1");
        comment4.setUser(user);
        comment4.setScore(60);
        comment4.setId("1");
        comment4.setIlike(1);
        comment4.setCtime(new java.sql.Timestamp(2014121123));
        Set<Comment> set2 = new HashSet<>();
        set2.add(comment);
        set2.add(comment2);
        set2.add(comment4);
        Info info2 = new Info();
        info2.setUser(user2);
        info2.setComment(comment2);
        info2.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info2.setAddress("aaa");
        info2.setBrief("this is brief");
        info2.setCmcount(12);
        info2.setTitle("this is title");
        info2.setName("abc");
        info2.setContent("this is content2");
        info2.setComments(set2);
        info2.setCleancontent("this is c content");
        list.add(info);
        list.add(info2);
        Log.d("InfosFragment", "Socket Type: " + info.getCleancontent()+"111");
    }

    public String NetType(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            String typeName = info.getTypeName().toLowerCase(); // WIFI/MOBILE
            if (typeName.equalsIgnoreCase("wifi")) {
            } else {
                typeName = info.getExtraInfo().toLowerCase();
                // 3gnet/3gwap/uninet/uniwap/cmnet/cmwap/ctnet/ctwap
            }
            return typeName;
        } catch (Exception e) {
            return null;
        }
    }

    /*@Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getNewCatNames());
                mListView.setAdapter(mAdapter);
                mInfoRefreshWidget.setRefreshing(false);
            });
    }*/

    private class LoadDataTask extends AsyncTask<String, Void, InfoPage> {
        private String actiontype = "refresh";//load

        public LoadDataTask() {
            page = 0;
        }

        public LoadDataTask(String actiontype) {
            if (StringUtils.equals(actiontype, "refresh"))
                page = 0;
            this.actiontype = actiontype;
        }

        @Override
        protected InfoPage doInBackground(String... params) {
            InfoPage pagesupport = null;
            RestTemplate restTemplate = new RestTemplate();
            FastJsonHttpMessageConverter c = new FastJsonHttpMessageConverter();
            c.setFeatures(SerializerFeature.UseISO8601DateFormat);
            restTemplate.getMessageConverters().add(c);
            //restTemplate.getMessageConverters()
      //      pagesupport = restTemplate.getForObject("http://10.0.2.2:8080/wxine_m/home.htm?page={page}", InfoPage.class, page+1);

            /*JSONObject jsonObject = null;
            JSONObject ops;

            HttpURLConnection conn = null; //连接对象
            InputStream is = null;
            String resultData = "";
            try {
                URL url = new URL("http://10.0.2.2:8080/wxine_m/home.htm"); //URL对象
                conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接
                conn.setDoInput(true); //允许输入流，即允许下载
                conn.setDoOutput(true); //允许输出流，即允许上传
                conn.setUseCaches(false); //不使用缓冲
                conn.setRequestMethod("GET"); //使用get请求
                is = conn.getInputStream();   //获取输入流，此时才真正建立链接
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine  = "";
                while((inputLine = bufferReader.readLine()) != null){
                    resultData += inputLine + "\n";
                }
                jsonObject = new JSONObject(resultData);

                ops = jsonObject.getJSONObject("ps");
                pagesupport = JSON.parseObject(ops.toString(), InfoPage.class, Feature.AllowISO8601DateFormat);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally{
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(conn != null){
                    conn.disconnect();
                }
            }*/
            //Log.v("===========", resultData);

            return pagesupport;
        }

        @Override
        protected void onPostExecute(final InfoPage result) {
            if (null != result) {
                Log.v("---------", "download ok");
                if (StringUtils.equals(actiontype, "load"))
                    mAdapter.addItems(result.getItems());
                else
                    mAdapter.setList(result.getItems());
                //mAdapter.notifyDataSetChanged();
                page = result.getCurrentPage();
                isLoading = false;
                mRefreshLayout.setRefreshing(false);
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
