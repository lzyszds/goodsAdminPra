module.exports = {
    isDelete: function (event) {
        let is = false
        console.log(event)
        layui.use(function () {
            const layer = layui.layer;
            layer.confirm(`确定要删除这个用户吗？`, {icon: 3}, function () {
                layer.msg('删除成功！！', {icon: 1});
                is = true
            }, function () {
                layer.msg('取消！！');
            });
        })
        return is;
    },
    heartbeat: function () {
        setInterval(() => {
            fetch()
        }, 1000 * 60)
    }
}