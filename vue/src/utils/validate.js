import {
  Message
} from 'element-ui';

export function isvalidUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}

/* 合法uri*/
export function validateURL(textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

/* 小写字母*/
export function validateLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/* 大写字母*/
export function validateUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/* 大小写字母*/
export function validatAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}


let verify = {
  nullFun: function(t) {
    return (t.use() === 'ch' ? 　"不能为空！" : " can not be empty!");
  },
  formatErrorFun: function(t) {
    return (t.use() === 'ch' ? 　"格式错误！" : " wrong format!");
  },
  fe: function(m, a, b) {
    return (m + "只能为" + (a + "") + " ~ " + (b + '') + "之间的数字！");
  },
  isIp: function(s) {
    var y = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    return y.test(s) ? true : false;
  },
  isMac: function(s) {
    var y = /^[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}$/;
    return y.test(s) ? true : false;
  },
  isNull: function(a) {
    return (a === "" || a.length === 0 || a === null || a === undefined ? true : false);
  },
  isNumber: function(a) {
    return !this.isNull(a) && !isNaN(a) ? true : false;
  },
  between: function(a, b, c) {
    return (!this.isNull(a) && !this.isNull(b) && !this.isNull(c) && this.isNumber(a) && this.isNumber(b) && this.isNumber(c) && b <= a && c >= a) ? true : false;
  },
  betweenN: function(a, b, c) {
    return (this.isNumberN(a) && this.isNumberN(b) && this.isNumberN(c) && b <= a && c >= a) ? true : false;
  },
  isNumberN: function(a) {
    return !isNaN(a) ? true : false;
  },
  checkEmail: function(str) {
    var re = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
    return !re.test(str) ? true : false;
  },
  isPoneAvailable: function(str) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    return !myreg.test(str) ? true : false;
  },
  isIDCard: function(str) {
    var myisIDCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return !myisIDCard.test(str) ? true : false;
  }, //身份证

  isBianMa: function(str) {
    var re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/g;
    return !re.test(str) ? true : false;
  },
  isChinese: function(str) {　
    var re = new RegExp("[\\u4E00-\\u9FFF]+", "g");
    return !re.test(str) ? false : true;
  },
  isWeUrl: function(str) {　
    var re = /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
    return !re.test(str) ? true : false;
  },


}

function validaTree(str) {
  if (!str || verify.isNull(str.label)) {
    Message({
      message: '名称不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.menuUrl)) {
    Message({
      message: '路径不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.icon)) {
    Message({
      message: '图标不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validaManageUser(str) {
  if (!str || verify.isNull(str.userName)) {

    Message({
      message: '用户名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.cellphone)) {
    Message({
      message: '手机号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.cellphone)) {
    Message({
      message: '手机号格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.email)) {
    Message({
      message: '邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.email)) {
    Message({
      message: '请输入正确的邮箱地址！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.password)) {
    Message({
      message: '密码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isBianMa(str.password)) {
    Message({
      message: '密码必须包含至少一个字母和一个数字,长度大于6位！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.selected)) {
    Message({
      message: '角色不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.orgId)) {
    Message({
      message: '组织架构不能为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validatoRegistere(str) {
  if (!str || verify.isNull(str.email)) {
    Message({
      message: '邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.email)) {
    Message({
      message: '请输入正确的邮箱地址！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.password)) {
    Message({
      message: '密码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isBianMa(str.password)) {
    Message({
      message: '密码必须包含至少一个字母和一个数字,长度大于6位！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.repassword)) {
    Message({
      message: '请再次输入密码！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '名字不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '唯一识别码不能为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validatoRegistere2(str) {
  if (!str || verify.isNull(str.email)) {
    Message({
      message: '邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.email)) {
    Message({
      message: '请输入正确的邮箱地址！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.password)) {
    Message({
      message: '密码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isBianMa(str.password)) {
    Message({
      message: '密码必须包含至少一个字母和一个数字,长度大于6位！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.repassword)) {
    Message({
      message: '请再次输入密码！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '名字不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '身份证不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isIDCard(str.code)) {
    Message({
      message: '请输入正确的身份证格式！',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validacolumn(str) {
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '分类编码不能为空！',
      type: 'error'
    });
    return false;
  }
  // if (!str || verify.isBianMa(str.code)) {
  //   Message({
  //     message: '分类编码必须英文字母+数字的组合！',
  //     type: 'error'
  //   });
  //   return false;
  // }
  if (!str || verify.isChinese(str.code)) {
    Message({
      message: '分类编码不能含有中文！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.name)) {
    Message({
      message: '分类名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (str.onlyUrl == 1) {
    if (!str || verify.isNull(str.contentUrl)) {
      Message({
        message: '外链地址不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isWeUrl(str.contentUrl)) {
      Message({
        message: '外链地址格式不正确！',
        type: 'error'
      });
      return false;
    }
  }
  return true;
}

function validacontent(str) {
  if (!str || verify.isNull(str.title)) {
    Message({
      message: '标题不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.categroyId)) {
    Message({
      message: '所属分类不能为空！',
      type: 'error'
    });
    return false;
  }
  if (str.onlyUrl == 1) {
    if (!str || verify.isNull(str.contentUrl)) {
      Message({
        message: '外链地址不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isWeUrl(str.contentUrl)) {
      Message({
        message: '外链地址格式不正确！',
        type: 'error'
      });
      return false;
    }

  }
  if (str.copied == 1) {
    if (!str || verify.isNull(str.copyFrom)) {
      Message({
        message: '来源不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.copyFromUrl)) {
      Message({
        message: '来源网址不能为空！',
        type: 'error'
      });
      return false;
    }

    if (!str || verify.isWeUrl(str.copyFromUrl)) {
      Message({
        message: '来源网址格式不正确！',
        type: 'error'
      });
      return false;
    }
  }
  if (!str || verify.isNull(str.covers)) {
    Message({
      message: '封面不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.description)) {
    Message({
      message: '描述不能为空！',
      type: 'error'
    });
    return false;
  }

  if (str.publishNow == 1) {
    if (!str || verify.isNull(str.publishDate)) {
      Message({
        message: '发布日期不能为空！',
        type: 'error'
      });
      return false;
    }

  }
  return true;
}

function validacontent2(str) {
  if (!str || verify.isNull(str.title)) {
    Message({
      message: '标题不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.categroyId)) {
    Message({
      message: '所属分类不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.covers)) {
    Message({
      message: '封面不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.description)) {
    Message({
      message: '描述不能为空！',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validactive(str) {
  if (!str || verify.isNull(str.title)) {
    Message({
      message: '标题不能为空！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.subject)) {
    Message({
      message: '主题不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.exAddr)) {
    Message({
      message: '活动地址不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.enrollStart)) {
    Message({
      message: '报名开始时间不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.enrollEnd)) {
    Message({
      message: '报名结束时间不能为空！',
      type: 'error'
    });
    return false;
  }

  if (!str || !(str.enrollStart <= str.enrollEnd)) {
    Message({
      message: '报名结束时间必须大于报名开始时间！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.exStart)) {
    Message({
      message: '活动开始时间不能为空！',
      type: 'error'
    });
    return false;
  }

  if (!str || !(str.enrollStart <= str.exStart)) {
    Message({
      message: '活动开始时间必须大于报名开始时间！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.exEnd)) {
    Message({
      message: '活动结束时间不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || !(str.enrollEnd <= str.exEnd)) {
    Message({
      message: '活动结束时间必须大于报名结束时间！',
      type: 'error'
    });
    return false;
  }
  if (!str || !(str.exStart <= str.exEnd)) {
    Message({
      message: '活动结束时间必须大于活动开始时间！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.orgContacts)) {
    Message({
      message: '主办方联系人不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.orgPhone)) {
    Message({
      message: '主办方联系电话不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.orgPhone)) {
    Message({
      message: '主办方联系电话格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (str.publishNow == 1) {
    if (!str || verify.isNull(str.publishDate)) {
      Message({
        message: '发布时间不能为空！',
        type: 'error'
      });
      return false;
    }
  }
  return true;
}
function validresetAdminPW(str, checkStatus, cellphone) {

  if (!str || verify.isNull(str.userName)) {
    Message({
      message: '用户名不能为空！',
      type: 'error'
    });
    return false;
  }
    if (!str || verify.isNull(cellphone)) {
      Message({
        message: '手机号不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isPoneAvailable(cellphone)) {
      Message({
        message: '手机号格式不正确！',
        type: 'error'
      });
      return false;
    }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '验证码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.newPassword)) {
    Message({
      message: '新密码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isBianMa(str.newPassword)) {
    Message({
      message: '密码必须必须包含至少一个字母和一个数字,长度大于6位！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.rePassword)) {
    Message({
      message: '请重复输入新密码！',
      type: 'error'
    });
    return false;
  }
  return true;
}
function validresetPW(str, checkStatus, cellphone) {
  if (checkStatus == 0) {
    if (!str || verify.isNull(str.email)) {
      Message({
        message: '邮箱不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.checkEmail(str.email)) {
      Message({
        message: '请输入正确的邮箱地址！',
        type: 'error'
      });
      return false;
    }
  }
  if (checkStatus == 1) {
    if (!str || verify.isNull(cellphone)) {
      Message({
        message: '手机号不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isPoneAvailable(cellphone)) {
      Message({
        message: '手机号格式不正确！',
        type: 'error'
      });
      return false;
    }
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '验证码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.newPassword)) {
    Message({
      message: '新密码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isBianMa(str.newPassword)) {
    Message({
      message: '密码必须包含至少一个字母和一个数字,长度大于6位！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.rePassword)) {
    Message({
      message: '请重复输入新密码！',
      type: 'error'
    });
    return false;
  }
  return true;
}


function validaExpert(str) {
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '姓名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.sex)) {
    Message({
      message: '性别不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '身份证号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isIDCard(str.code)) {
    Message({
      message: '请输入正确的身份证格式！',
      type: 'error'
    });
    return false;
  }


  if (!str || verify.isNull(str.edu)) {
    Message({
      message: '学历不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.academic)) {
    Message({
      message: '学位不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.research_field)) {
    Message({
      message: '研究领域不能为空！',
      type: 'error'
    });
    return false;
  }
  if (str.research_field == 99) {
    if (!str || verify.isNull(str.researchFieldOther)) {
      Message({
        message: '研究领域其他不能为空！',
        type: 'error'
      });
      return false;
    }
  }
  if (!str || verify.isNull(str.mobilephone)) {
    Message({
      message: '手机号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.mobilephone)) {
    Message({
      message: '手机号格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.fdemail)) {
    Message({
      message: '邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.fdemail)) {
    Message({
      message: '请输入正确的邮箱地址！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.country)) {
    Message({
      message: '区域不能为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validaEnterprise(str) {
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '企业名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '企业编码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.registered_capital)) {
    Message({
      message: '企业规模不能为空！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.registered_type)) {
    Message({
      message: '注册类型不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.country)) {
    Message({
      message: '所在地区不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.registerMarket)) {
    Message({
      message: '必须选择是否上市！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.domain)) {
    Message({
      message: '所属领域不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.lpname)) {
    Message({
      message: '法人姓名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.lxname)) {
    Message({
      message: '联系人姓名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.lxphone)) {
    Message({
      message: '联系人手机号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.lxphone)) {
    Message({
      message: '联系人手机号格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.lxemail)) {
    Message({
      message: '联系人邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.lxemail)) {
    Message({
      message: '请输入正确的联系人邮箱地址！',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validaHschool(str) {
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '统一社会信用代码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.country)) {
    Message({
      message: '所在地区不能为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validaMechanism(str) {
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '单位名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '统一社会信用代码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.country)) {
    Message({
      message: '所在地区不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.orgType)) {
    Message({
      message: '机构类别不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.fdname)) {
    Message({
      message: '法定代表人姓名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.linkman)) {
    Message({
      message: '联系人姓名不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.lxphone)) {
    Message({
      message: '联系人手机号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.lxphone)) {
    Message({
      message: '联系人手机号格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.lxemail)) {
    Message({
      message: '联系人邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.lxemail)) {
    Message({
      message: '请输入正确的联系人邮箱地址！',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validaDemandLibrary(str) {
  if (!str || verify.isNull(str.re_name)) {
    Message({
      message: '技术需求名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '需求单位名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '统一社会信用代码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.country)) {
    Message({
      message: '所在地区不能为空！',
      type: 'error'
    });
    return false;
  }
  var flag = false
  if (!str || !verify.isNull(str.fzname) || !verify.isNull(str.fzphone) || !verify.isNull(str.fzemail)) {
    if (!str || verify.isNull(str.fzname)) {
      Message({
        message: '负责人姓名不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.fzphone)) {
      Message({
        message: '负责人电话不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isPoneAvailable(str.fzphone)) {
      Message({
        message: '负责人电话格式不正确！',
        type: 'error'
      });
      return false;
    }

    if (!str || verify.isNull(str.fzemail)) {
      Message({
        message: '负责人邮箱不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.checkEmail(str.fzemail)) {
      Message({
        message: '请输入正确的负责人邮箱地址！',
        type: 'error'
      });
      return false;
    }
    flag = true
  }

  if (!str || !verify.isNull(str.fzname2) || !verify.isNull(str.fzphone2) || !verify.isNull(str.fzemail2)) {
    if (!str || verify.isNull(str.fzname2)) {
      Message({
        message: '联系人姓名不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.fzphone2)) {
      Message({
        message: '联系人电话不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isPoneAvailable(str.fzphone2)) {
      Message({
        message: '联系人电话格式不正确！',
        type: 'error'
      });
      return false;
    }

    if (!str || verify.isPoneAvailable(str.fzphone2)) {
      Message({
        message: '联系人电话格式不正确！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.fzemail2)) {
      Message({
        message: '联系人邮箱不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.checkEmail(str.fzemail2)) {
      Message({
        message: '请输入正确的联系人邮箱地址！',
        type: 'error'
      });
      return false;
    }
    flag = true
  }
  if (!flag) {
    Message({
      message: '负责人和联系人信息不能同时为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validaAchieveLibrary(str) {
  if (!str || verify.isNull(str.re_name)) {
    Message({
      message: '技术成果名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '企业名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '统一社会信用代码不能为空！',
      type: 'error'
    });
    return false;
  }
  var flag = false
  if (!str || !verify.isNull(str.fzname) || !verify.isNull(str.fzphone) || !verify.isNull(str.fzemail)) {
    if (!str || verify.isNull(str.fzname)) {
      Message({
        message: '负责人姓名不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.fzphone)) {
      Message({
        message: '负责人电话不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isPoneAvailable(str.fzphone)) {
      Message({
        message: '负责人电话格式不正确！',
        type: 'error'
      });
      return false;
    }

    if (!str || verify.isNull(str.fzemail)) {
      Message({
        message: '负责人邮箱不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.checkEmail(str.fzemail)) {
      Message({
        message: '请输入正确的负责人邮箱地址！',
        type: 'error'
      });
      return false;
    }
    flag = true
  }

  if (!str || !verify.isNull(str.fzname2) || !verify.isNull(str.fzphone2) || !verify.isNull(str.fzemail2)) {
    if (!str || verify.isNull(str.fzname2)) {
      Message({
        message: '联系人姓名不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.fzphone2)) {
      Message({
        message: '联系人电话不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isPoneAvailable(str.fzphone2)) {
      Message({
        message: '联系人电话格式不正确！',
        type: 'error'
      });
      return false;
    }

    if (!str || verify.isPoneAvailable(str.fzphone2)) {
      Message({
        message: '联系人电话格式不正确！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.isNull(str.fzemail2)) {
      Message({
        message: '联系人邮箱不能为空！',
        type: 'error'
      });
      return false;
    }
    if (!str || verify.checkEmail(str.fzemail2)) {
      Message({
        message: '请输入正确的联系人邮箱地址！',
        type: 'error'
      });
      return false;
    }
    flag = true
  }
  if (!flag) {
    Message({
      message: '负责人和联系人信息不能同时为空！',
      type: 'error'
    });
    return false;
  }

  if (!str || verify.isNull(str.domain)) {
    Message({
      message: '所属领域不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.stage)) {
    Message({
      message: '所处阶段不能为空！',
      type: 'error'
    });
    return false;
  }
  return true;
}


function validmajorPlatform(str) {
  if (!str || verify.isNull(str.spName)) {
    Message({
      message: '名称不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.spCompany)) {
    Message({
      message: '承担单位不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.spDomain)) {
    Message({
      message: '领域不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.spLevel)) {
    Message({
      message: '级别不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.spLink)) {
    Message({
      message: '链接不能为空！',
      type: 'error'
    });
    return false;
  }
  return true;
}


function validaUserBaseDetail(str) {

  if (!str || verify.isNull(str.cellphone)) {
    Message({
      message: '手机号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.cellphone)) {
    Message({
      message: '手机号格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.email)) {
    Message({
      message: '邮箱不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.checkEmail(str.email)) {
    Message({
      message: '请输入正确的邮箱地址！',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validachangePWD(str) {

  if (!str || verify.isNull(str.newPassword)) {
    Message({
      message: '密码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isBianMa(str.newPassword)) {
    Message({
      message: '密码必须包含至少一个字母和一个数字,长度大于6位！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.rePassword)) {
    Message({
      message: '请再次输入密码！',
      type: 'error'
    });
    return false;
  }

  return true;
}
//success/warning/info/error
export default {
  validachangePWD,
  validaUserBaseDetail,
  validmajorPlatform,
  validaExpert,
  validaEnterprise,
  validaHschool,
  validaMechanism,
  validaDemandLibrary,
  validaAchieveLibrary,
  validresetAdminPW,
  validresetPW,
  validaTree,
  validatoRegistere,
  validatoRegistere2,
  validaManageUser,
  validacolumn,
  validacontent,
  validacontent2,
  validactive
}
