package autoHeroJson;

public enum JsonNodesFields {

    RESPONSE {
        public String toString() {
            return "response";
        }
    },
    HITS {
        public String toString() {
            return "hits";
        }
    },
    REGISTRATION_YEAR {
        public String toString() {
            return "firstRegistrationYear";
        }
    },
    OFFER_PRICE {
        public String toString() {
            return "offerPrice";
        }
    },
    AMOUNT_MINOR_UNITS {
        public String toString() {
            return "amountMinorUnits";
        }
    },
    SOURCE {
        public String toString() {
            return "_source";
        }
    }
}
