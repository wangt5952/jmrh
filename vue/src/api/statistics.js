import request from '@/utils/request'


export function gettjlib(num) {
  return request({
    url: '/data/lib?formType='+num,
    method: 'get',
  });
}


export function gettjlibqy(data,num) {
  return request({
    url: '/data/lib?formType='+num,
    method: 'post',
    data
  });
}


export function gettjUser(data) {
  return request({
    url: '/data/user',
    method: 'get',
  });
}


export function gettjUserqy(data) {
  return request({
    url: '/data/user',
    method: 'post',
    data
  });
}

export function gettjvisiters(data) {
  return request({
    url: '/data/visiters',
    method: 'get',
  });
}
export function gettjmeeting(data) {
  return request({
    url: '/data/meeting',
    method: 'get',
  });
}
