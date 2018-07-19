import request from '@/utils/request'


export function getMain(id) {//用户详情
  return request({
    url: '/mdm/main',
    method: 'get'
  });
}
