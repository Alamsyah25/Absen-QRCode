<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_Crud extends CI_Model {

	function get_all_mahasiswa(){
        $hasil=$this->db->get('mahasiswa');
        return $hasil;
    }
    public function selectResult($id){
        $this->db->where($id);
        return $this->db->get('tamu');   
    }
    public function select()
    {
        return $this->db->get('tamu');
    }
    public function selectWhere($id)
    {
        $this->db->where($id);
        return $this->db->get('tamu');
    }
    public function getTableWhere($id)
    {
        $this->db->where($id);
        return $this->db->get('tamu');
    }
    public function getTableWhereTmp($id)
    {
        $this->db->where($id);
        return $this->db->get('tmp');
    }
    public function autokode()
    {
      $this->db->select_max('id', 'a');
      $query = $this->db->get('tamu')->row_array();
      $kode = (int) substr($query['a'], 3);
      $number = $kode + 1;
      return $autokode  = "DSP".sprintf("%04s",$number);

    }
    public function updateTamu($id,$data)
    {
        $this->db->where($id);
        return $this->db->update('tamu', $data);
    }
    public function simpan($data)
    {
        return $this->db->insert('tamu', $data);
    }
    public function insert($data){
        return $this->db->insert('tmp', $data);
    }
    // function simpan_tamu($nama,$email,$image_name){
    //     $data = array(
    //         'nama'      => $nama,
    //         'email'     => $email, 
    //         'qr_code'   => $image_name
    //     );
    //     $this->db->insert('tamu',$data);
    // }
    public function absen($nim,$nama)
    {
        $data = ['nim' => $nim,'nama' => $nama];
        return $this->db->insert('tbl_absen',$data);
    }
    
}

/* End of file M_Crud.php */
/* Location: ./application/models/M_Crud.php */