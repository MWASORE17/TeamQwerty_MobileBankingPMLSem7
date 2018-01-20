<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
Route::post('login', 'RekeningController@loginRequest');
Route::post('transfer','RekeningController@transfer');
Route::post('historitransaksi','RekeningController@getHistoriTransaksi');
Route::post('updatekodeakses','RekeningController@updateKodeAkses');
Route::post('getkodeakses','RekeningController@getKodeAkses');
Route::post('getmpin','RekeningController@getMPin');
Route::post('updatempin','RekeningController@updateMPin');


