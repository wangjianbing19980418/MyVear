package cn.rongcloud.im.ui.fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.rongcloud.im.R;
import cn.rongcloud.im.server.SealAction;
import cn.rongcloud.im.server.network.async.AsyncTaskManager;
import cn.rongcloud.im.server.network.async.OnDataListener;
import cn.rongcloud.im.server.network.http.HttpException;
import cn.rongcloud.im.server.response.DefaultConversationResponse;
import cn.rongcloud.im.server.utils.NToast;
import cn.rongcloud.im.ui.activity.MyView;
import io.rong.imlib.RongIMClient;

public class DiscoverFragment extends Fragment implements View.OnClickListener, OnDataListener {

    private static final int GETDEFCONVERSATION = 333;
    private AsyncTaskManager atm = AsyncTaskManager.getInstance(getActivity());
    private ArrayList<DefaultConversationResponse.ResultEntity> chatroomList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatroom_list, container, false);
        initViews(view);
        atm.request(GETDEFCONVERSATION, this);
        return view;
    }

    private void initViews(View view) {
        LinearLayout chatroomItem1 = (LinearLayout) view.findViewById(R.id.def_chatroom1);
        LinearLayout chatroomItem2 = (LinearLayout) view.findViewById(R.id.def_chatroom2);
        LinearLayout chatroomItem3 = (LinearLayout) view.findViewById(R.id.def_chatroom3);
        LinearLayout chatroomItem4 = (LinearLayout) view.findViewById(R.id.def_chatroom4);
        chatroomItem1.setOnClickListener(this);
        chatroomItem2.setOnClickListener(this);
        chatroomItem3.setOnClickListener(this);
        chatroomItem4.setOnClickListener(this);
        //回调时的线程并不是UI线程，不能在回调中直接操作UI
        RongIMClient.getInstance().setChatRoomActionListener(new RongIMClient.ChatRoomActionListener() {
            @Override
            public void onJoining(String chatRoomId) {

            }

            @Override
            public void onJoined(String chatRoomId) {

            }

            @Override
            public void onQuited(String chatRoomId) {

            }

            @Override
            public void onError(String chatRoomId, final RongIMClient.ErrorCode code) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == RongIMClient.ErrorCode.RC_NET_UNAVAILABLE || code == RongIMClient.ErrorCode.RC_NET_CHANNEL_INVALID) {
                            NToast.shortToast(getActivity(), getString(R.string.network_not_available));
                        } else {
                            NToast.shortToast(getActivity(), getString(R.string.fr_chat_room_join_failure));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (chatroomList == null || chatroomList.get(0) == null) {
            NToast.shortToast(getActivity(), getString(R.string.join_chat_room_error_toast));
            return;
        }
        switch (v.getId()) {
            case R.id.def_chatroom1:
//                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.CHATROOM, chatroomList.get(0).getId(), "聊天室 I");
//                doStartApplicationWithPackageName("cn.rongcloud.live", App.phone, App.password);
                Intent mIntent=new Intent(getActivity(), MyView.class);
                startActivity(mIntent);
                break;
            case R.id.def_chatroom2:
//                doStartApplicationWithPackageName("cn.rongcloud.live", App.phone, App.password);
//                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.CHATROOM, chatroomList.get(1).getId(), "聊天室 II");
                mIntent=new Intent(getActivity(), MyView.class);
                startActivity(mIntent);
                break;
            case R.id.def_chatroom3:
                VideodoStartApplicationWithPackageName("");
//                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.CHATROOM, chatroomList.get(2).getId(), "聊天室 III");
                break;
            case R.id.def_chatroom4:
                VideodoStartApplicationWithPackageName("");
//                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.CHATROOM, chatroomList.get(3).getId(), "聊天室 IV");
                break;
        }
    }
    private void VideodoStartApplicationWithPackageName(String pS) {
        Intent mIntent = new Intent();
        ComponentName comp = new ComponentName("vitamio.vitamiolibrary","vitamio.vitamiolibrary.MainActivity");
        mIntent.setComponent(comp);

        mIntent.setAction("android.intent.action.MAIN");

        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            startActivity(mIntent);
            return ;
        }
        else{
            new AlertDialog.Builder(getActivity())
                    .setMessage("您还没有安装Video播放器，请先安装")
                    .setTitle("安装提示")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("安装", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://118.89.22.167/javaclub/video-release.apk"));
                            startActivity(intent);
                        }
                    })
                    .show();
            return;
        }
    }

    private void doStartApplicationWithPackageName(String packagename, String phoneNumber, String password) {

    Intent intent = new Intent();
    intent.setClassName(packagename,packagename+".ui.LoginActivity");
    // Verify it resolves
    PackageManager packageManager = getActivity().getPackageManager();
    List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        Bundle mBundle = new Bundle();
        mBundle.putString("phone", phoneNumber);
        mBundle.putString("password", password);
        intent.putExtras(mBundle);
// Start an activity if it's safe
    if(isIntentSafe)
    {
        startActivity(intent);
    }
        else{
        new AlertDialog.Builder(getActivity())
                .setMessage("您还没有安装直播器，请先安装")
                .setTitle("安装提示")
                .setNegativeButton("取消",null)
                .setPositiveButton("安装", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://118.89.22.167/javaclub/mylibs-release.apk"));
                        startActivity(intent);
                    }
                })
                .show();
        return;
    }

    }


    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        return new SealAction(getActivity()).getDefaultConversation();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int requestCode, Object result) {
        DefaultConversationResponse response = (DefaultConversationResponse) result;
        if (response.getCode() == 200) {
            ArrayList<DefaultConversationResponse.ResultEntity> resultEntityArrayList = new ArrayList();
            chatroomList = new ArrayList();
            if (response.getResult().size() > 0) {
                resultEntityArrayList.clear();
                chatroomList.clear();
                for (DefaultConversationResponse.ResultEntity d : response.getResult()) {
                    if (d.getType().equals("group")) {
                        resultEntityArrayList.add(d);
                    } else {
                        chatroomList.add(d);
                    }
                }
            }
        }
    }

    @Override
    public void onFailure(int requestCode, int state, Object result) {

    }


}
