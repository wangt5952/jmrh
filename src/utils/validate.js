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
    return (a === "" || a === null || a === undefined ? true : false);
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

function validaRole(str) {
  if (!str || verify.isNull(str.roleName)) {
    Message({
      message: '名称不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
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


function validausr(str) {
  if (!str || verify.isNull(str.userName)) {
    Message({
      message: '用户名不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.userPassword)) {
    Message({
      message: '密码不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validadep(str) {
  if (!str || verify.isNull(str.label)) {
    Message({
      message: '名称不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.departCode)) {
    Message({
      message: '编号不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validamdm(str) {
  if (!str) {
    Message({
      message: '请输入内容！',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validaserviceInter(str) {
  if (!str || verify.isNull(str.wsdlLocation)) {
    Message({
      message: 'WSDL地址不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.dburl)) {
    Message({
      message: '数据库地址不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.username)) {
    Message({
      message: '用户名不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.password)) {
    Message({
      message: '密码不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validaservice(str) {
  if (!str || verify.isNull(str.bsName)) {
    Message({
      message: '业务名不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validamodelDiy(str) {
  if (!str || verify.isNull(str.modelId)) {
    Message({
      message: '请选择模型',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.tableName)) {
    Message({
      message: '数据库表名不能为空',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.tableLabel)) {
    Message({
      message: '中文名表名不能为空',
      type: 'error'
    });
    return false;
  }
  return true;
}

function validamodule(str) {
  if (!str || verify.isNull(str.mdmModel)) {
    Message({
      message: '模块名不能为空',
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
  if (!str || verify.isNull(str.ecode)) {
    Message({
      message: '验证码不能为空！',
      type: 'error'
    });
    return false;
  }

  return true;
}

function validatoRegisterp(str) {
  if (!str || verify.isNull(str.phone)) {
    Message({
      message: '手机号不能为空！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isPoneAvailable(str.phone)) {
    Message({
      message: '手机号格式不正确！',
      type: 'error'
    });
    return false;
  }
  if (!str || verify.isNull(str.verity)) {
    Message({
      message: '验证码不能为空！',
      type: 'error'
    });
    return false;
  }
  return true;
}
//success/warning/info/error
export default {
  validaRole,
  validaTree,
  validausr,
  validadep,
  validamdm,
  validaserviceInter,
  validaservice,
  validamodelDiy,
  validamodule,
  validatoRegistere,
  validatoRegisterp
}
