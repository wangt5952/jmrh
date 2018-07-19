import request from '@/utils/request'


export function getdefinedId(id) {//用户详情
  return request({
    url: '/mdm/table/defined/'+id,
    method: 'get'
  });
}
export function adddefinedId(data,id) {//用户详情
  return request({
    url: '/mdm/table/defined/'+id,
    method: 'post',
    data
  });
}
export function editdefinedId(data,id) {//用户详情
  return request({
    url: '/mdm/table/defined/'+id,
    method: 'put',
    data
  });
}
export function deldefinedId(lid,id) {
  return request({
    url: '/mdm/table/defined/'+id+'?id='+lid,
    method: 'delete',
  });
}
export function loadPush(lid,id) {
  return request({
    url: '/mdm/dataMap/send?menuId='+lid,
    method: 'post',
  });
}
export function loadPull(lid,id) {
  return request({
    url: '/mdm/dataMap/sync?menuId='+lid,
    method: 'post',
  });
}
