package orderProcessing.orderProcessingService;

class QuantityNotInInventoryException extends RuntimeException{
    QuantityNotInInventoryException(Long id) {
        super("Could not fill order quantity" + id);
    }
}
