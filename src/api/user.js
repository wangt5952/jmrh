import request from '@/utils/request'


export function getUser(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/user/getPage?page='+data.page+'&pageSize='+data.limit+'&userName='+data.objName,
    method: 'get',
    data
  });
}
export function addUser(data) {
  return request({
    url: '/mdm/user',
    method: 'post',
    data
  });
}
export function saveUser(data) {
  return request({
    url: '/mdm/user',
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
    url: '/mdm/user?id='+id,
    method: 'delete',

  });
}
