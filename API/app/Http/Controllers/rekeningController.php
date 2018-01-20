<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\rekening;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;

class RekeningController extends Controller
{
    /**
     * Show the profile for the given user.
     *
     * @param  int  $id
     * @return Response
     */
    public function tambahSaldo(Request $request)
    {
        $no_rekening = $request->no_rekening;
        $nominal = $request->nominal;
        
        if(empty($no_rekening)||empty($nominal)){
            $response = [
                "status" => "false",
                "message" => "data tidak lengkap"
            ];
        }
        else{
            $response = [
                "status" => "true",
                "message" => "Penambahan saldo suksess",
                "data" => rekening::tambahSaldo($no_rekening,$nominal)
            ];
        }
        return response($response);
    }
    
    public function getKodeAkses(Request $request){
        $no_rekening = $request -> no_rekening;
        $response = [
            "status"=>"true",
            "data" => rekening::getKodeAkses($no_rekening)
        ];
        return response($response); 
    }
    
    public function loginRequest(Request $request){
        $validator = Validator::make($request->all(),[
            'no_rekening'=>'required|min:11|max:11',
            'kode_akses'=>'required|min:6'
        ]);
        if($validator ->fails()){
            $response = [
                "status" => "false",
                "message" => $validator->messages()->first()
            ];
            return response($response);
        }
        $no_rekening = $request -> no_rekening;
        $kode_akses = $request -> kode_akses;
        if(!rekening::getNoRekening($no_rekening)){
            $response = [
                "status" => "false",
                "message" => "Nomor rekening tidak terdaftar."
            ];
            return response($response);
        }
        else if(rekening::getKodeAkses($no_rekening)!=$kode_akses){
            $response = [
                "status" => "false",
                "message" => "Kode akses salah."
            ];
            return response($response);
        }
        else{
            $response = [
                "status" => "true",
                "message" => "Login berhasil.",
                "data" => rekening::getRekeningInfo($no_rekening)
            ];
            return response($response);
        }
    }
    
    public function transfer(Request $request){
        $no_rekening = $request -> no_rekening;
        $nominal = $request -> nominal;
        $rek_tujuan = $request -> rek_tujuan;
        $catatan = $request -> catatan;
        $saldo = rekening::getSaldo($no_rekening);
        if($saldo-$nominal<0){
            $response = [
                "status" => "false",
                "message" => "Saldo tidak mencukupi"
            ];
        }
        else if(rekening::getNoRekening($no_rekening)){
            rekening::transferDalam($no_rekening,$nominal,$rek_tujuan,$catatan);
            $response=[
                "status" => "true",
                "message" => "Transfer berhasil",
                "dataRekening" => rekening::getRekeningInfo($no_rekening),
                "dataHistori" => rekening::getHistoriTransaksi($no_rekening)
            ];
        }
        else{
            rekening::transferLuar($no_rekening,$nominal,$rek_tujuan,$catatan);
            $response=[
                "status" => "true",
                "message" => "Transfer berhasil",
                "dataRekening" => rekening::getRekeningInfo($no_rekening),
                "dataHistori" => rekening::getHistoriTransaksi($no_rekening)
            ];
        }
        return response($response);
    }
    public function getHistoriTransaksi(Request $request){
        $no_rekening = $request -> no_rekening;
        $response=[
                "status" => "true",
                "message" => "Histori Transaksi Berhasil diperoleh",
                "dataRekening" => rekening::getRekeningInfo($no_rekening),
                "dataHistori" => rekening::getHistoriTransaksi($no_rekening)
            ];
        return response($response);
    }
    
    public function updateKodeAkses(Request $request){
        $no_rekening = $request -> no_rekening;
        $kode_akses_lama = $request -> kode_akses_lama;
        $kode_akses_baru = $request -> kode_akses_baru;
        if(!rekening::getNoRekening($no_rekening)){
            $response = [
                "status" => "false",
                "message" => "Nomor rekening tidak terdaftar"
            ];
        }
        else if(rekening::getKodeAkses($no_rekening)!=$kode_akses_lama){
            $response = [
                "status" => "false",
                "message" => "Kode akses lama salah"
            ];
        }
        else{
            rekening::updateKodeAkses($no_rekening,$kode_akses_baru);
            $response = [
                "status" => "true",
                "message" => "Kode akses berhasil diubah"
            ];
        }
        return response($response);
    }
    
    public function getMPin(Request $request){
        $no_rekening = $request -> no_rekening;
        if(rekening::getNoRekening($no_rekening)){
            $response = [
                "status" => "true",
                "message" => "mPin berhasil diperoleh",
                "data" => rekening::getPin($no_rekening)
            ];
        }
        else{
            $response = [
                "status" => "false",
                "message" => "mPin gagal diperoleh",
                "data" => "failed"
            ];
        }
        return response($response);
    }
    
    public function updateMPin(Request $request){
        $no_rekening = $request -> no_rekening;
        $mPin_lama = $request -> mPin_lama;
        $mPin_baru = $request -> mPin_baru;
        if(!rekening::getNoRekening($no_rekening)){
            $response = [
                "status" => "false",
                "message" => "Nomor rekening tidak terdaftar"
            ];
        }
        else if(rekening::getPin($no_rekening)!=$mPin_lama){
            $response = [
                "status" => "false",
                "message" => "mPin lama salah"
            ];
        }
        else{
            rekening::updateMPin($no_rekening,$mPin_baru);
            $response = [
                "status" => "true",
                "message" => "mPin berhasil diubah"
            ];
        }
        return response($response);
    }
    
    public function register(Response $response){
        $no_rekening = $request -> no_rekening;
        $saldo = $request -> saldo;
        $kode_akses = $request -> kode_akses;
        $mpin = $request -> mpin;
        $mbanking = $request -> mbanking;
        rekening::register($no_rekening,$saldo,$kode_akses,$mpin,$mbanking);
    }
    
}