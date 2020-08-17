/*
* 创建sse会话
* */
let source = null;
let id=null;

export default {
  source:source,
  id:id,
  sourceInit:function () {
    this.id=new Date().getTime()
    this.source=new EventSource('http://localhost:8088/subscribe/' + this.id)
    /**
     * 连接一旦建立，就会触发open事件
     * 另一种写法：source.onopen = function (event) {}
     */
    this.source.addEventListener('open', function (e) {// eslint-disable-line no-unused-vars
      console.log("建立连接。。。");
    }, false);

    /**
     * 客户端收到服务器发来的数据
     * 另一种写法：source.onmessage = function (event) {}
     */
    //this.source.addEventListener('bing', this.bingListener)


    /**
     * 如果发生通信错误（比如连接中断），就会触发error事件
     * 或者：
     * 另一种写法：source.onerror = function (event) {}
     */
    this.source.addEventListener('error', function (e) {
      if (e.readyState === EventSource.CLOSED) {
        console.log("连接关闭");
      } else {
        console.log(e);
      }
    }, false);
  },

  getSource:function () {
    return this.source
  },
  getID:function () {
    return this.id;
  }
}
