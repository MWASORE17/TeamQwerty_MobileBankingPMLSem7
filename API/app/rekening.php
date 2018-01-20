<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;
use DB;

class rekening extends Model
{
    public $incrementing = 'false';
    protected $primaryKey = 'no_rekening';
    protected $keyType = 'string';
    protected $fillable = ['no_rekening','saldo','mpin','mbanking'];
    protected $hidden = ['mpin'];
    
    public static function transferLuar($no_rekening, $nominal,$rek_tujuan,$catatan)
    {
        $saldo = DB::table('rekening')->where('no_rekening',$no_rekening)->value('saldo');
        DB::table('rekening')->where('no_rekening',$no_rekening)->update(['saldo'=>$saldo-$nominal]);
        DB::table('transaksi')->insertGetId([
            'rek_tujuan' => $rek_tujuan,
            'nominal' => $nominal,
            'no_rekening' => $no_rekening,
            'catatan' => $catatan,
            'tanggal' => Carbon::now('Asia/Jakarta')
        ]);
        $data = DB::table('rekening')->where('no_rekening',$no_rekening)->first();
        return $data;
    }
    public static function transferDalam($no_rekening, $nominal, $rek_tujuan,$catatan)
    {
        $saldoRek = DB::table('rekening')->where('no_rekening',$no_rekening)->value('saldo');
        DB::table('rekening')->where('no_rekening',$no_rekening)->update(['saldo'=>$saldoRek-$nominal]);
        $saldoRekT = DB::table('rekening')->where('no_rekening',$rek_tujuan)->value('saldo');
        DB::table('rekening')->where('no_rekening',$rek_tujuan)->update(['saldo'=>$saldoRekT+$nominal]);
        DB::table('transaksi')->insertGetId([
            'rek_tujuan' => $rek_tujuan,
            'nominal' => $nominal,
            'no_rekening' => $no_rekening,
            'catatan' => $catatan,
            'tanggal' => Carbon::now('Asia/Jakarta')
        ]);
        $data = DB::table('rekening')->where('no_rekening',$no_rekening)->first();
        return $data;
    }
    
    public static function getRekeningInfo($noRekening){
        $data = DB::table('rekening')
            ->join('nasabah','rekening.id_nasabah','=','nasabah.id_nasabah')
            ->select('rekening.no_rekening AS noRek',
                     'rekening.kode_akses AS kodeAkses',
                     'rekening.saldo AS saldo',
                     'nasabah.namanasabah AS nama',
                     'rekening.mpin AS pin')
            ->where('rekening.no_rekening',$noRekening)
            ->first();
        return $data;
    }
    
    public static function getNoRekening($noRekening){
        return DB::table('rekening')->where('no_rekening',$noRekening)->first();
    }
    public static function getKodeAkses($noRekening){
        return DB::table('rekening')->where('no_rekening',$noRekening)->value('kode_akses');
    }
    public static function getSaldo($noRekening){
        return DB::table('rekening')->where('no_rekening',$noRekening)->value('saldo');
    }
    public static function getPin($noRekening){
        return DB::table('rekening')->where('no_rekening',$noRekening)->value('mpin');
    }
    public static function getHistoriTransaksi($no_rekening){
        $data = DB::table('transaksi')
            ->where('no_rekening',$no_rekening)
            ->orWhere('rek_tujuan',$no_rekening)
            ->orderBy('id_transaksi','desc')
            ->select('no_rekening AS pengirim', 
                     'rek_tujuan AS penerima',
                     'catatan AS jenisTransaksi',
                     'nominal AS jumlahTransaksi',
                     'tanggal AS tanggalTransaksi')
            ->get();
        return $data;
    }
    
    public static function updateKodeAkses($no_rekening,$kode_akses){
        $data = DB::table('rekening')
            ->where('no_rekening',$no_rekening)
            ->update(['kode_akses'=>$kode_akses]);
    }
    public static function updateMPin($no_rekening,$mpin){
        $data = DB::table('rekening')
            ->where('no_rekening',$no_rekening)
            ->update(['mpin'=>$mpin]);
    }
    public static function register($no_rekening,$saldo,$kode_akses,$mpin,$mbanking){
        DB::table('rekening')
            ->insertGetId([
                'no_rekening' =>no_rekening,
                'saldo' => saldo,
                'kode_akses' => kode_akses,
                'mpin' => mpin,
                'mbanking' => mbanking
            ]);
    } 
}
