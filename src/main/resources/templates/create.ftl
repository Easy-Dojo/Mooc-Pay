<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>支付</title>
</head>
<body>
<div id="qrCodeCanvas"></div>
<div id="orderId">${orderId}</div>

<script src="https://cdn.bootcss.com/jquery/1.6.2/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
  $('#qrCodeCanvas').qrcode({
    text: '${codeUrl}',
  })
  $(function () {
    setInterval(function () {
      console.log('开始查询订单状态...')
      $.ajax({
        url: '/pay/queryByOrderId',
        data: {
          'orderId': $('#orderId').text(),
        },
        success: function (result) {
          if (result.platformStatus != null && result.platformStatus === 'SUCCESS') {
            location.href = 'http://127.0.0.1'
          }
        },
        error: function (result) {
          console.log(result)
          alert('支付失败！')
        },
      })
    }, 2000)
  })
</script>
</body>
</html>
