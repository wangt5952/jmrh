import request from '@/utils/request'


export function getAll(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/table/getAll',
    method: 'get',
    data
  });
}
export function getAllone(id) {
  return request({
    //url: '/user/login',
    url: '/mdm/table?id=' + id,
    method: 'get'
  });
}

export function addTableRelation(data) {
  return request({
    url: '/mdm/table/addTableRelation',
    method: 'post',
    data
  });
}

export function getAlldel(id) {
  return request({
    //url: '/user/login',
    url: '/mdm/table?id=' + id,
    method: 'delete'
  });
}
export function getBasicData(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/table/getBasicData',
    method: 'get',
    data
  });
}
export function addTable(data) {
  return request({
    url: '/mdm/table',
    method: 'post',
    data
  });
}
export function editTable(data) {
  return request({
    url: '/mdm/table',
    method: 'put',
    data
  });
}
export function delTable(data) {
  return request({
    url: '/mdm/table/delColumn',
    method: 'delete',
    data
  });
}

export function modelID(id) {
  return request({
    url: '/mdm/model?id=' + id,
    method: 'get'
  });
}
export function getAllFlowData(modelId,bsId) {
  return request({
    url: '/mdm/flow/getAllFlowData',
    method: 'get'
  });
}



//模块接口
export function modelAll(data) {
  return request({
    url: '/mdm/model/getAll',
    method: 'get',
    data
  });
}
// export function editModel(data) {
//   return request({
//     url: '/mdm/model',
//     method: 'put',
//     data
//   });
// }
export function addModel(data) {
  return request({
    url: '/mdm/model',
    method: 'post',
    data
  });
}
export function delModel(id) {
  return request({
    url: '/mdm/model?id=' + id,
    method: 'delete'
  });
}
