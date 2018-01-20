<?php

namespace App\Http\Controllers;

use App\nasabah;
use App\Http\Controllers\Controller;

class NasabahController extends Controller
{
    /**
     * Show the profile for the given user.
     *
     * @param  int  $id
     * @return Response
     */
    public function show($id)
    {
        $nasabah = nasabah::find($id);
        return response()->json($nasabah);
    }
}