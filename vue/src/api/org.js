import request from '@/utils/request'




export function getOrgMenus(data) {
  return request({
    url: '/xtcx/sysManager/org/tree',
    method: 'get',
    data
  });
}

export function addCreate(data) {
  return request({
    url: '/xtcx/sysManager/org',
    method: 'post',
    data
  });
}
export function saveEdit(data) {
  return request({
    url: '/xtcx/sysManager/org',
    method: 'post',
    data
  });
}
export function delOrg(id) {
  return request({
    url: '/xtcx/sysManager/org?id='+id,
    method: 'delete',

  });
}
