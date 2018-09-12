import request from '@/utils/request'


export function getMeeting(data) {
  return request({
    url: '/xtcx/meeting/userpage',
    method: 'post',
    data
  });
}


export function saveMeeting(data) {
  return request({
    url: '/xtcx/meeting/reapply',
    method: 'put',
    data
  });
}


export function getMeetingC(id) {//获取详情
  return request({
    url: '/xtcx/meeting?id='+id,
    method: 'get'
  });
}


export function rejectrefuse(data) {//拒绝
  return request({
    url: '/xtcx/refuse',
    method: 'post',
    data
  });
}


export function rejectagree(data) {//同意
  return request({
    url: '/xtcx/agree',
    method: 'post',
    data
  });
}
