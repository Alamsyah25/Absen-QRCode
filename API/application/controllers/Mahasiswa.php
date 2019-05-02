<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Mahasiswa extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		$this->load->model('m_crud');
	}
	public function index()
	{
		$data['data']=$this->m_crud->select();
		$this->load->view('mahasiswa_view', $data);
        }
        function simpan(){

                $kode = $this->m_crud->autokode();
		$nama=$this->input->post('nama');
                $email=$this->input->post('email');
                $company = $this->input->post('company');
                $this->load->library('ciqrcode'); //pemanggilan library QR CODE
         
                $config['cacheable']    = true; //boolean, the default is true
                $config['cachedir']     = './asset/'; //string, the default is application/cache/
                $config['errorlog']     = './asset/'; //string, the default is application/logs/
                $config['imagedir']     = './asset/images/'; //direktori penyimpanan qr code
                $config['quality']      = true; //boolean, the default is true
                $config['size']         = '1024'; //interger, the default is 1024
                $config['black']        = array(224,255,255); // array, default is array(255,255,255)
                $config['white']        = array(70,130,180); // array, default is array(0,0,0)
                $this->ciqrcode->initialize($config);
         
                $image_name=$nama.'_'.$email.'_'.time().'.png'; //buat name dari qr code sesuai dengan nim
                
                $params['data'] = base64_encode($kode."DISTRUP"); //data yang akan di jadikan QR CODE
                $params['level'] = 'H'; //H=High
                $params['size'] = 10;
                $params['savename'] = FCPATH.$config['imagedir'].$image_name; //simpan image QR CODE ke folder asset/images/
                $this->ciqrcode->generate($params); // fungsi untuk generate QR CODE
                
               $data = array(
                'id' => $kode,
                'hash_code' => base64_encode($kode."DISTRUP"),
            'nama'      => $nama,
            'email'     => $email,
            'company'   => $company,
            'qr_code'   => $image_name
        );

                $this->m_crud->simpan($data); //simpan ke database
            
                redirect('mahasiswa'); //redirect ke mahasiswa usai simpan data
        	}

}

/* End of file Mahasiwa.php */
/* Location: ./application/controllers/Mahasiwa.php */