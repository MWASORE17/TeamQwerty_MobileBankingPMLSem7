<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class transaksi extends Model
{
    /*protected $primaryKey = 'id_transaksi';
    protected $table = 'transaksi';
    protected $fillable = ['no_rekening','rek_tujuan','nominal','catatan', 'tanggal'];
    public static function getHistoriTransaksi($no_rekening){
        $data = DB::table('transaksi')
            ->where('no_rekening',$no_rekening)
            ->orWhere('rek_tujuan',$no_rekening)
            ->orderBy('id_transaksi','asc')
            ->select('no_rekening AS pengirim', 
                     're_tujuan AS penerima',
                     'catatan AS jenisTransaksi',
                     'nominal AS jumlahTransaksi',
                     'tanggal AS tanggalTransaksi')
            -first();
    }*/
}
