<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>支付</title>
</head>
<body>
<div id="qrCodeCanvas"></div>

<script src="https://cdn.bootcss.com/jquery/1.6.2/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
  $('#qrCodeCanvas').qrcode({
    text: 'https://www.baidu.com',
  })
</script>
</body>
</html>
