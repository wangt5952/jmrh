import request from '@/utils/request'


export function getSubmitLibSum(data) {
  return request({
    url: '/xtcx/lib/getSubmitLibSum',
    method: 'get',

  });
}
