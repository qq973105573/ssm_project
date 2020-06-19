let vm = new Vue({
    el:'#main-container',
    data:{
        app:''
    },
    methods:{
        doUpdate:function(){
            //更新
            axios({
                url:'manager/app/doUpdate',
                method:'put',
                data:this.app
            }).then(response=>{
                if(!response.data.success){
                    //失败需要重新同步页面数据
                    parent.layer.success = false;
                }
                let index = parent.layer.getFrameIndex(window.name);//根据子窗口名字获取索引值
                parent.layer.close(index);//通过父窗口layer关闭子窗口
                parent.layer.msg(response.data.msg);//这里处理异常结果
            }).catch(error=>{//后台抛出错误无法被ExceptionController捕获处理的时候进入
                layer.msg(error.message);
            })
        },
        //取消按钮逻辑
        toClose:function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    created:function () {
        /**
         * parent:父窗口windows对象  index  自动封装
         * 子窗口是iframe  update.html
         *
         * parent.layer:父窗口中的layer对象   index中的layer对象
         * @type {Object}
         */
        this.app = parent.layer.obj;
    }
})