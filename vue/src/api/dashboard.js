import request from '@/utils/request'


export function getSubmitLibSum(data) {
  return request({
    url: '/xtcx/lib/getSubmitLibSum',
    method: 'get',

  });
}
export function getSubmitLib(data) {
  return request({
    url: '/xtcx/lib/getSubmitLib',
    method: 'get',

  });
}

export function getMyTaskSum(data) {
  return request({
    url: '/xtcx/category/getMyTaskSum',
    method: 'get',

  });
}


export function getSubmitCmsSum(data) {
  return request({
    url: '/xtcx/exchanges/getSubmitCmsSum',
    method: 'get',

  });
}
