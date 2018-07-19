/**
 * Created by gyx on 18/03/16.
 */

export function parseTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (('' + time).length === 10) time = parseInt(time) * 1000
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return time_str
}

export function formatTime(time, option) {
  time = +time * 1000
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) { // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    return parseTime(time, option)
  } else {
    return d.getMonth() + 1 + '月' + d.getDate() + '日' + d.getHours() + '时' + d.getMinutes() + '分'
  }
}


function _newVals() {
  var deviceContent = $scope.deviceContent;
  var obj = [];
  for (var x in deviceContent) {
    var data = {};
    data.name = x;
    data.value = deviceContent[x];
    obj.push(data);
  }
  localStorage.setItem('valueDoms', JSON.stringify(obj));
}

function _changeVals(x) {
  var deviceContent = x;
  var obj = [];
  for (var x in deviceContent) {
    var data = {};
    data.name = x;
    data.value = deviceContent[x];
    obj.push(data);
  }
  var _vals = JSON.parse(localStorage.getItem('valueDoms')),
    changeObj = {};
  for (var i = 0; i < obj.length; i++) {
    var _id = obj[i].name,
      _val = obj[i].value;
    for (var j = 0; j < _vals.length; j++) {
      var _id2 = _vals[j].name,
        _val2 = _vals[j].value;
      if (_id === _id2) {
        if (_val != _val2) {
          changeObj[_id] = _val;
        }
      }
    }
  }
  return changeObj;
}
