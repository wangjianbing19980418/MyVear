<?php
namespace Common\Controller;

use Common\Controller\HomebaseController2;

class MemberbaseController2 extends HomebaseController2{

    protected $user_model;
    protected $user;
    protected $userid;

    function _initialize() {
        parent::_initialize();

        $this->check_login();
        $this->check_user();
        //by Rainfer <81818832@qq.com>
        if(sp_is_user_login()){
            $this->userid=sp_get_current_userid();
            $this->users_model=D("Common/Zhubo");
            $this->user=$this->users_model->where(array("id"=>$this->userid))->find();
        }
    }

}