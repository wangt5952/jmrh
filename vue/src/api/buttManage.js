import request from '@/utils/request'


export function getMeeting(data) {
  return request({
    url: '/meeting/userpage',
    method: 'post',
    data
  });
}


export function saveMeeting(data) {
  return request({
    url: '/meeting/reapply',
    method: 'put',
    data
  });
}


export function getMeetingC(id) {//获取详情
  return request({
    url: '/meeting?id='+id,
    method: 'get'
  });
}


export function rejectrefuse(data) {//拒绝
  return request({
    url: '/refuse',
    method: 'post',
    data
  });
}


export function rejectagree(data) {//同意
  return request({
    url: '/agree',
    method: 'post',
    data
  });
}
