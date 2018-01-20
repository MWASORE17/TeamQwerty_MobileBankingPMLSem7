<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class nasabah extends Model
{
    protected $primaryKey = 'id_nasabah';
    protected $table = 'nasabah';
    protected $fillable = ['namanasabah','alamat','namaibu','noidentitas','umur','nohp'];
}
