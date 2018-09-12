import request from '@/utils/request'


export function getShareplatform(data) {
  return request({
    url: '/xtcx/shareplatform/page?pageNum='+data.pageNum+'&pageSize='+data.pageSize+'&queryStr='+data.queryStr,
    method: 'get',
  });
}


export function addShareplatform(data) {
  return request({
    url: '/xtcx/shareplatform/add',
    method: 'post',
    data
  });
}

export function updShareplatform(data) {
  return request({
    url: '/xtcx/shareplatform/upd',
    method: 'put',
    data
  });
}

export function getShareplatformC(id) {//获取详情
  return request({
    url: '/xtcx/shareplatform/get?id='+id,
    method: 'get'
  });
}

export function delShareplatformC(id) {//获取详情
  return request({
    url: '/xtcx/shareplatform/del?id='+id,
    method: 'delete'
  });
}
