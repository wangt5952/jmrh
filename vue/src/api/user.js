import request from '@/utils/request'


export function getUser(data) {
  return request({
    url: '/sysManager/bususer/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
export function setStatus(data) {
  return request({
    url: '/sysManager/bususer/status',
    method: 'put',
    data
  });
}
export function getUserDetailByUserId(id) {//用户详情
  return request({
    url: '/lib/getUserDetailByUserId?userId='+id,
    method: 'get',
  });
}

// export function addUser(data) {
//   return request({
//     url: '/sysManager/user',
//     method: 'post',
//     data
//   });
// }
// export function saveUser(data) {
//   return request({
//     url: '/sysManager/user',
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
    url: '/user/resetPwd',
    method: 'post',
    data
  });
}
export function sendEmailCode(data) {
  return request({
    url: '/user/sendEmailCode',
    method: 'post',
    data
  });
}
export function sendCellphoneCode(data) {
  return request({
    url: '/user/sendCellphoneCode',
    method: 'post',
    data
  });
}
export function lookPwd(data) {
  return request({
    url: '/user/lookPwd',
    method: 'post',
    data
  });
}
export function lookAdminPwd(data) {
  return request({
    url: '/user/lookAmdinPwd',
    method: 'post',
    data
  });
}
