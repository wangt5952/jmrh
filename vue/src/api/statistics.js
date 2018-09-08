import request from '@/utils/request'


export function gettjlib(data) {
  return request({
    url: '/xtcx/data/lib?formType=1',
    method: 'get',
  });
}


export function gettjlibqy(data) {
  return request({
    url: '/xtcx/data/lib?formType=1',
    method: 'post',
    data
  });
}
