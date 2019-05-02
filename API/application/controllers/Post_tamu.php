<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Post_tamu extends CI_Controller {
	public function __construct()
	{
		parent::__construct();
		$this->load->model('M_Crud');
	}
	public function validasiStatus(){
		$hash_code = $this->input->post('hash_code');
		$where = ['hash_code' => $hash_code];
		$cek = $this->M_Crud->selectResult($where)->row_array();
		$status = $cek['status'];
		if ($status != 1) {
			$response['status'] = $status;
			$response['stat'] = true;
			$response['kode'] = 200;
			$response['msg'] = "Success";
		} else {
			$response['status'] = $status;
			$response['stat'] = false;
			$response['kode'] = 500;
			$response['msg'] = "Tamu Sudah Absen!";
		}
		echo json_encode($response);
	}
	public function update()
	{
		$response = [];
		date_default_timezone_set("Asia/Jakarta");

		// $id = $this->input->post('id');
		$hash_code = $this->input->post('hash_code');
		$date = date('Y-m-d h:i:s');
		$where = ['hash_code' => $hash_code];
		$cek = $this->M_Crud->getTableWhere($where)->row_array();
		if ($cek) {
			$response['kode'] = 200;	
			$data = ['status' => 1, 'tgl_hadir' => $date];
			$tes = $this->M_Crud->updateTamu($where,$data);
			if ($tes) {
				$response['stat'] = true;
				$response['kode'] = 200;
				$response['msg'] = "Success";
			} else {
				$response['stat'] = false;
				$response['kode'] = 500;
				$response['msg'] = "Ada Kesalahan!";
			}
			
		} else {
			$response['stat'] = false;
			$response['kode'] = 500;
			$response['msg'] = "Tamu Tidak Terdaftar!";
		}
		echo json_encode($response);
		
	}
	public function validasiPlace(){
		$response = [];
		$hash_code = $this->input->post('hash_code');
		$place = $this->input->post('place');
		$where = ['hash_code' => $hash_code];
		$cek = $this->M_Crud->getTableWhereTmp($where)->row_array();
		
		if ($cek['place'] != $place) {			
			$response['place'] = $cek['place'];
			$response['stat'] = true;
			$response['kode'] = 200;
			$response['msg'] = "Success";
		} else {
			$response['place'] = $cek['place'];
			$response = $cek;
			$response['stat'] = false;
			$response['kode'] = 500;
			$response['msg'] = "Failed!";
		}
		echo json_encode($response);
	}
	public function place(){
		$response = [];
		$hash_code = $this->input->post('hash_code');
		$place = $this->input->post('place');	
		$where = ['hash_code' => $hash_code];
		// $place = "1";
			$data = ['hash_code' => $hash_code, 'place' => $place];
			$ex = $this->M_Crud->insert($data);
			$cek = $this->M_Crud->getTableWhereTmp($where)->row_array();
			// $kode = base64_decode($cek['id']);
			if ($ex) {
				$response = $cek;
				$response['stat'] = true;
				$response['kode'] = 200;
				$response['msg'] = "Success";
			} else {
				$response = $cek;
				$response['stat'] = false;
				$response['kode'] = 500;
				$response['msg'] = "Ada Kesalahan!";
			}
			
		
		echo json_encode($response);
	}
	public function selectWhere()
	{
		$response = [];
		$hash_code = $this->input->post('hash_code');
		$where = ['hash_code' => $hash_code];
		$data = $this->M_Crud->selectWhere($where)->row_array();
		if ($data) {
				$response['stat'] = true;
				$response['kode'] = 200;
				$response['msg'] = "Success";
				$response = $data;
		} else {
				$response['stat'] = false;
				$response['kode'] = 500;
				$response['msg'] = "Ada Kesalahan";
				$response =$data;
		}

		
		echo json_encode($response);
	}

}



/* End of file Post_tamu.php */
/* Location: ./application/controllers/Post_tamu.php */