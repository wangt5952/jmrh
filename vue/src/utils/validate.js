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
  }

}

function validaManageUser(str) {
  debugger
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


function validacolumn(str) {
  if (!str || verify.isNull(str.code)) {
    Message({
      message: '栏目编码不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.name)) {
    Message({
      message: '栏目名不能为空！',
      type: 'error'
    });
    return false;
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
      message: '所属栏目不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.publishDate)) {
    Message({
      message: '发布时间不能为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validacontent2(str) {
  if (!str || verify.isNull(str.cover)) {
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
  debugger
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
  if (!str || verify.isNull(str.exStart)) {
    Message({
      message: '活动开始时间不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.exEnd)) {
    Message({
      message: '报名结束时间不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.publishDate)) {
    Message({
      message: '发布时间不能为空！',
      type: 'error'
    });
    return false;
  }
  return true;
}
//success/warning/info/error
export default {
  validatoRegistere,
  validaManageUser,
  validacolumn,
  validacontent,
  validacontent2,
  validactive
}
