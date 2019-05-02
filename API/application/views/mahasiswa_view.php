<?php 

// echo substr("DSP0001DISTRUP", 3,4);
 ?>
<!DOCTYPE html>
<html>
<head>
    <title>Data Mahasiswa</title>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url().'asset/css/bootstrap.css'?>">
</head>
<body>
    <div class="container">
        <div class="row">
            <h2>Data <small>Mahasiswa</small></h2>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Add New</button>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>NIM</th>
                        <th>NAMA</th>
                        <th>COMPANY</th>
                        <th>QR CODE</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach($data->result() as $row):?>
                    <tr>
                        <td style="vertical-align: middle;"><?php echo $row->id;?></td>
                        <td style="vertical-align: middle;"><?php echo $row->nama;?></td>
                        <td style="vertical-align: middle;"><?php echo $row->company;?></td>
                        <td><img style="width: 100px;" src="<?php echo base_url().'asset/qrcode/05 ID PAY/'.$row->qr_code;?>"></td>
                    </tr>
                    <?php endforeach;?>
                </tbody>
            </table>
        </div>
    </div>
 
    <!-- Modal add new mahasiswa-->
    <form action="<?php echo base_url().'index.php/mahasiswa/simpan'?>" method="post">
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Add New Mahasiswa</h4>
              </div>
              <div class="modal-body">
             
                  <div class="form-group">
                    <input type="hidden" name="autokode" class="form-control" id="autokode">
                  </div>
                  <div class="form-group">
                    <label for="nama" class="control-label">NAMA:</label>
                    <input type="text" name="nama" class="form-control" id="nama">
                  </div>
                  <div class="form-group">
                    <label for="company" class="control-label">COMPANY:</label>
                    <input type="text" name="company" class="form-control" id="company">
                  </div>
                   <div class="form-group">
                    <label for="email" class="control-label">EMAIL:</label>
                    <input type="text" name="email" class="form-control" id="email">
                  </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Tutup</button>
                <button type="submit" class="btn btn-primary">Simpan</button>
              </div>
            </div>
          </div>
        </div>
    </form>
 
    <script type="text/javascript" src="<?php echo base_url().'asset/js/jquery-1.10.2.js'?>"></script>
    <script type="text/javascript" src="<?php echo base_url().'asset/js/bootstrap.js'?>"></script>
</body>
</html>