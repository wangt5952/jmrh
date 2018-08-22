import request from '@/utils/request'


export function getUser(data) {
  return request({
    url: '/xtcx/sysManager/bususer/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
export function setStatus(data) {
  return request({
    url: '/xtcx/sysManager/bususer/status',
    method: 'put',
    data
  });
}


// export function addUser(data) {
//   return request({
//     url: '/xtcx/sysManager/user',
//     method: 'post',
//     data
//   });
// }
// export function saveUser(data) {
//   return request({
//     url: '/xtcx/sysManager/user',
//     method: 'put',
//     data
//   });
// }
export function getUserId(id) {//用户详情
  return request({
    url: '/mdm/user?id='+id,
    method: 'get',
  });
}
export function delUser(id) {
  return request({
    url: '/mdm/user?id='+id,
    method: 'delete',

  });
}

export function resetPwd(data) {
  return request({
    url: '/xtcx/user/resetPwd',
    method: 'post',
    data
  });
}
export function sendEmailCode(data) {
  return request({
    url: '/xtcx/user/sendEmailCode',
    method: 'post',
    data
  });
}
export function sendCellphoneCode(data) {
  return request({
    url: '/xtcx/user/sendCellphoneCode',
    method: 'post',
    data
  });
}
export function lookPwd(data) {
  return request({
    url: '/xtcx/user/lookPwd',
    method: 'post',
    data
  });
}
