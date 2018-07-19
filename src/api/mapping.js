import request from '@/utils/request'


//结构映射接口
export function model_bsID(modelId,bsId) {
  return request({
    url: '/mdm/dataMap?modelId=' + modelId+'&bsId='+bsId,
    method: 'get'
  });
}

export function model_dataMap(data) {
  return request({
    url: '/mdm/dataMap',
    method: 'post',
    data
  });
}
export function delmodel_dataMap(data) {
  return request({
    url: '/mdm/dataMap',
    method: 'delete',
    data
  });
}
export function delDataMapMdm(data) {
  return request({
    url: '/mdm/dataMap/delDataMapMdm',
    method: 'delete',
    data
  });
}


//数据映射接口
export function dataMap(modelId,bsId,isSelect,page,pageSize) {
  return request({
    url: '/mdm/dataMap/getDataMapMdm?modelId=' + modelId+'&bsId='+bsId+'&isSelect='+isSelect+'&order=1&page='+page+'&pageSize='+pageSize,
    method: 'get'
  });
}


export function data_dataMap(data) {
  return request({
    url: 'mdm/dataMap/saveDataMapMdm',
    method: 'post',
    data
  });
}
export function deldata_dataMap(data) {
  return request({
    url: '/mdm/dataMap',
    method: 'delete',
    data
  });
}
