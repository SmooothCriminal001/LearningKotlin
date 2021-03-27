package unitTesting

//A top level function that calls on Airport's getAirportData method
//And sorts the returned airports by their name
fun getAirportStatus(codes: List<String>): List<Airport> =
    Airport.sort(
        codes.map{ code -> Airport.getAirportData(code) }
    )
