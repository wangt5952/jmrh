import request from '@/utils/request'


export function getServiceInterface(data) {
  return request({
    url: '/mdm/serviceInterface/getPage?page='+data.page+'&pageSize='+data.limit+'&bsId='+data.objName,
    method: 'get'
  });
}
export function addserviceInterface(data) {
  return request({
    url: '/mdm/serviceInterface',
    method: 'post',
    data
  });
}
export function editserviceInterface(data) {
  return request({
    url: '/mdm/serviceInterface',
    method: 'put',
    data
  });
}
// export function getBs(id) {
//   return request({
//         url: '/mdm/model/getBs?id='+id,
//     method: 'get'
//   });
// }
//

// export function saveUser(data) {
//   return request({
//     url: '/mdm/user',
//     method: 'put',
//     data
//   });
// }
// export function delBs(id) {
//   return request({
//     url: '/mdm/model/delBs?id='+id,
//     method: 'delete',
//
//   });
// }
