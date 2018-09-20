import request from '@/utils/request'


export function getSubmitLibSum(data) {
  return request({
    url: '/lib/getSubmitLibSum',
    method: 'get',

  });
}
export function getSubmitLib(data) {
  return request({
    url: '/lib/getSubmitLib',
    method: 'get',

  });
}

export function getMyTaskSum(data) {
  return request({
    url: '/category/getMyTaskSum',
    method: 'get',

  });
}


export function getSubmitCmsSum(data) {
  return request({
    url: '/exchanges/getSubmitCmsSum',
    method: 'get',

  });
}
