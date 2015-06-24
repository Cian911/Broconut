<?php

if (isset($_FILES['imagedata'])) {
    $file = "images/" . substr(md5(rand() . time()), 0, 13) . ".png";
    $open = fopen($file, 'w');
    fclose($open);
    move_uploaded_file($_FILES['imagedata']['tmp_name'], $file);

    echo "http://broconut.com/app/" . $file;
}