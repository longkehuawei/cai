package com.ui.login;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;


import com.C;
import com.app.annotation.apt.Router;
import com.apt.TRouter;
import com.base.BaseActivity;
import com.ui.main.R;
import com.ui.main.databinding.LoginActBinding;

/**
 * Created by Administrator on 2016/1/14.
 */

@Router(C.LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter, LoginActBinding> implements LoginContract.View {
    boolean isLogin = true;

    @Override
    public int getLayoutId() {
        return R.layout.login_act;
    }

    @Override
    public void initView() {
        mViewBinding.loginBtnLogin.setOnClickListener(v -> doAction());
        mViewBinding.loginBtnRegist.setOnClickListener(v -> {
            isLogin = false;

        });
    }

    private void doAction() {
        String name = mViewBinding.loginactEdtUsername.getText().toString();
        String pass = mViewBinding.loginactEdtUserpwd.getText().toString();
        String msg = TextUtils.isEmpty(name) ? "用户名不能为空!" : TextUtils.isEmpty(pass) ? "密码不能为空!" : "";
        if (!TextUtils.isEmpty(msg)) showMsg(msg);
        else if (isLogin) mPresenter.login(name, pass);
        else mPresenter.sign(name, pass);
    }

    @Override
    public void loginSuccess() {
        TRouter.go(C.HOME);
    }

    @Override
    public void signSuccess() {
        isLogin = true;
        doAction();
    }

    @Override
    public void showMsg(String msg) {
        Snackbar.make(mViewBinding.loginBtnLogin, msg, Snackbar.LENGTH_LONG).show();
    }
}
