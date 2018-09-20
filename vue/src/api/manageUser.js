import request from '@/utils/request'



export function getUser(data) {
  return request({
    url: '/sysManager/user/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
export function addUser(data) {
  return request({
    url: '/sysManager/user',
    method: 'post',
    data
  });
}
export function setUserStatus(data) {
  return request({
    url: '/sysManager/sysuser/status',
    method: 'put',
    data
  });
}

export function saveUser(data) {
  return request({
    url: '/sysManager/user',
    method: 'put',
    data
  });
}
export function getUserId(id) {//用户详情
  return request({
    url: '/mdm/user?id='+id,
    method: 'get',
  });
}
export function delUser(id) {
  return request({
    url: '/sysManager/user?id='+id,
    method: 'delete',

  });
}
