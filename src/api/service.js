import request from '@/utils/request'


export function getAllBs(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/model/getAllBs',
    method: 'get',
    data
  });
}
export function getBs(id) {
  return request({
        url: '/mdm/model/getBs?id='+id,
    method: 'get'
  });
}

export function addBs(data) {
  return request({
    url: '/mdm/model/addBs',
    method: 'post',
    data
  });
}
// export function saveUser(data) {
//   return request({
//     url: '/mdm/user',
//     method: 'put',
//     data
//   });
// }
export function delBs(id) {
  return request({
    url: '/mdm/model/delBs?id='+id,
    method: 'delete',

  });
}
