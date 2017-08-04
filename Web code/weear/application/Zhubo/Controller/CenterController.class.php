<?php
namespace Zhubo\Controller;

use Common\Controller\MemberbaseController2;

class CenterController extends MemberbaseController2 {
	
	function _initialize(){
		parent::_initialize();
	}
	
    // 会员中心首页
	public function index() {
		$this->assign($this->user);
    	$this->display(':center');
    }
}
