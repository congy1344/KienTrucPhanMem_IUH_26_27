<?php
$host     = gethostname();
$php_ver  = phpversion();
$time     = date('d/m/Y H:i:s');
$env      = getenv('APP_ENV') ?: 'development';
?>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>PHP + Apache - Docker</title>
</head>
<body>
  <h1>Ung dung PHP chay tren Apache trong Docker</h1>
  <ul>
    <li><strong>PHP Version:</strong> <?= $php_ver ?></li>
    <li><strong>Hostname:</strong> <?= $host ?></li>
    <li><strong>Thoi gian:</strong> <?= $time ?></li>
    <li><strong>APP_ENV:</strong> <?= $env ?></li>
  </ul>

  <?php
    $items = ["Docker", "PHP 8.2", "Apache"];
    echo "<ul>";
    foreach ($items as $item) {
        echo "<li>$item</li>";
    }
    echo "</ul>";
  ?>
</body>
</html>
