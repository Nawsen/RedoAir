angular
  .module('app', ['ui.router', 'ngAnimate', 'ui.materialize', 'pascalprecht.translate'])
  .service();
angular
    .module('app').config(['$translateProvider', function ($translateProvider) {
    $translateProvider.translations('en', {
        'TITLE.SEARCH': 'Search',
        'TITLE.BOOKING': 'Booking',
        'DROPDOWN.MYBOOKINGS': 'My bookings',
        'USER.LOGOUT': 'Log out',
        'USER.LOGIN': 'Login',
        'USER.REGISTER': 'Register',
        'USER.ENTEREMAIL': 'Enter your email',
        'USER.ENTERFIRSTNAME': 'Enter your first name',
        'USER.ENTERLASTNAME': 'Enter your last name',
        'USER.ENTERPASSWORD': 'Enter your password',
        'INPUT.FILTER': 'Filter',
        'CC.NUMBER': 'Card number',
        'CC.CVC': 'CVC',
        'CC.EXPIRY_MONTH': 'Expiry month',
        'CC.EXPIRY_YEAR': 'Expiry year',
        'TERMS_AND_CONDITIONS': 'I agree to the Terms and Conditions',
        'PAYMENT.CC': 'Pay with Credit Card',
        'PAYMENT.TRANSFER': 'Pay with Bank Transfer',
        'CONFIRM': 'Confirm',
        'DEPARTURE': 'Departure',
        'ARRIVAL': 'Arrival',
        'IN': 'in',
        'TO': 'to',
        'ON': 'on',
        'TICKETS': 'Tickets',
        'TICKETSLOWERCASE': 'tickets',
        'TOTALPRICE': 'Total price',
        'ENGLISH': 'English',
        'DUTCH': 'Nederlands'
    });

    $translateProvider.translations('nl', {
        'TITLE.SEARCH': 'Zoeken',
        'TITLE.BOOKING': 'Booking',
        'DROPDOWN.MYBOOKINGS': 'Mijn bookings',
        'USER.LOGOUT': 'Afmelden',
        'USER.LOGIN': 'Aanmelden',
        'USER.REGISTER': 'Registreren',
        'USER.ENTEREMAIL': 'Voer uw e-mailadres in',
        'USER.ENTERFIRSTNAME': 'Voer uw voornaam in',
        'USER.ENTERLASTNAME': 'Voer uw familienaam in',
        'USER.ENTERPASSWORD': 'Voer uw wachtwoord in',
        'INPUT.FILTER': 'Filter',
        'CC.NUMBER': 'Kaartnummer',
        'CC.CVC': 'CVC',
        'CC.EXPIRY_MONTH': 'Vervalmaand',
        'CC.EXPIRY_YEAR': 'Vervaljaar',
        'TERMS_AND_CONDITIONS': 'Ik ga akkoord met de voorwaarden.',
        'PAYMENT.CC': 'Betaal met kredietkaart',
        'PAYMENT.TRANSFER': 'Betaal met overschrijving',
        'CONFIRM': 'Bevestigen',
        'DEPARTURE': 'Vertrek',
        'ARRIVAL': 'Aankomst',
        'IN': 'in',
        'TO': 'naar',
        'ON': 'op',
        'TICKETS': 'Tickets',
        'TICKETSLOWERCASE': 'tickets',
        'TOTALPRICE': 'Totale prijs',
        'ENGLISH': 'English',
        'DUTCH': 'Nederlands'
    });

    $translateProvider.preferredLanguage('en');
}]);
