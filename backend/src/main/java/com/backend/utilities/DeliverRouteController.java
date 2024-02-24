package com.backend.utilities;

import com.backend.constant.Constant;
import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

@RequestMapping("tools/deliver-route/")
@RestController
public class DeliverRouteController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerAndTransactionDeliverRepository customerAndTransactionDeliverRepository;

    @Autowired
    TransactionAndGatherDeliverRepository transactionAndGatherDeliverRepository;

    @Autowired
    GatherAndGatherDeliverRepository gatherAndGatherDeliverRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionPointLeaderRepository transactionPointLeaderRepository;

    @Autowired
    GatherPointLeaderRepository gatherPointLeaderRepository;

    @Autowired
    PackageBoxRepository packageBoxRepository;

    private Timestamp addAnHour(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);

        calendar.add(Calendar.HOUR, 1);

        return new Timestamp(calendar.getTimeInMillis());
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody DeliverRoute deliverRoute) {
        Integer DEFAULT_PRICE = 20000;
        Customer sendCustomer = customerRepository.findByUsername(deliverRoute.getSendCustomerUsername());
        Customer receiveCustomer = customerRepository.findByUsername(deliverRoute.getReceiveCustomerUsername());

        TransactionPointLeader firstTransactionLeader = transactionPointLeaderRepository.findByUsername(deliverRoute.getFirstTransactionPointCode());
        GatherPointLeader firstGatherLeader = gatherPointLeaderRepository.findByUsername(deliverRoute.getFirstGatherPointCode());
        GatherPointLeader secondGatherLeader = gatherPointLeaderRepository.findByUsername(deliverRoute.getSecondGatherPointCode());
        TransactionPointLeader secondTransactionLeader = transactionPointLeaderRepository.findByUsername(deliverRoute.getSecondTransactionPointCode());

        PackageBox packageBox = packageBoxRepository.findById(deliverRoute.getPackageBoxId()).get();

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp firstTimestamp = addAnHour(currentTimestamp);
        Timestamp secondTimestamp = addAnHour(firstTimestamp);
        Timestamp thirdTimestamp = addAnHour(secondTimestamp);
        Timestamp forthTimestamp = addAnHour(thirdTimestamp);
        Timestamp receiveTimestamp = addAnHour(forthTimestamp);

        Cart cart = new Cart(sendCustomer, receiveCustomer, packageBox, currentTimestamp, receiveTimestamp, deliverRoute.getState(), DEFAULT_PRICE);
        CustomerAndTransactionDeliver firstCustomerAndTransactionDeliver = new CustomerAndTransactionDeliver(sendCustomer, firstTransactionLeader, deliverRoute.getState(), currentTimestamp, firstTimestamp, packageBox);
        TransactionAndGatherDeliver firstTransactionAndGatherDeliver = new TransactionAndGatherDeliver(firstGatherLeader, firstTransactionLeader, deliverRoute.getState(), firstTimestamp, secondTimestamp, packageBox);
        GatherAndGatherDeliver gatherAndGatherDeliver = new GatherAndGatherDeliver(firstGatherLeader, secondGatherLeader, deliverRoute.getState(), secondTimestamp, thirdTimestamp, packageBox);
        TransactionAndGatherDeliver secondTransactionAndGatherDeliver = new TransactionAndGatherDeliver(secondGatherLeader, secondTransactionLeader, deliverRoute.getState(), thirdTimestamp, forthTimestamp, packageBox);
        CustomerAndTransactionDeliver secondCustomerAndTransactionDeliver = new CustomerAndTransactionDeliver(receiveCustomer, secondTransactionLeader, deliverRoute.getState(), forthTimestamp, receiveTimestamp, packageBox);

        if (!deliverRoute.isSuccessful())
            cart.setReceiveTime(null);
        cartRepository.save(cart);

        switch (deliverRoute.getState()) {
            case Constant.onDeliverCustomerToTransaction: case Constant.failedCustomerToTransaction:

                firstCustomerAndTransactionDeliver.setReceiveTime(null);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                break;
            case Constant.successfulTransactionStock:

                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                if (!Objects.isNull(secondGatherLeader) && !Objects.isNull(secondTransactionLeader)) {

                    firstTransactionAndGatherDeliver.setState(Constant.successfulGatherStock);
                    transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                    gatherAndGatherDeliver.setState(Constant.successfulGatherStock);
                    gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);

                    transactionAndGatherDeliverRepository.save(secondTransactionAndGatherDeliver);
                }
                break;
            case Constant.onDeliverTransactionToGather: case Constant.failedTransactionToGather:

                firstCustomerAndTransactionDeliver.setState(Constant.successfulTransactionStock);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                firstTransactionAndGatherDeliver.setReceiveTime(null);
                transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                break;
            case Constant.successfulGatherStock:
                firstCustomerAndTransactionDeliver.setState(Constant.successfulTransactionStock);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                if (!Objects.isNull(firstGatherLeader) && !Objects.isNull(secondGatherLeader)) {
                    gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);
                }
                break;
            case Constant.onDeliverToOtherGather: case Constant.failedToOtherGather:

                firstCustomerAndTransactionDeliver.setState(Constant.successfulTransactionStock);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                firstTransactionAndGatherDeliver.setState(Constant.successfulGatherStock);
                transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                gatherAndGatherDeliver.setReceiveTime(null);
                gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);

                break;
            case Constant.onDeliverGatherToTransaction: case Constant.failedGatherToTransaction:

                firstCustomerAndTransactionDeliver.setState(Constant.successfulTransactionStock);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                firstTransactionAndGatherDeliver.setState(Constant.successfulGatherStock);
                transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                gatherAndGatherDeliver.setState(Constant.successfulGatherStock);
                gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);

                secondTransactionAndGatherDeliver.setReceiveTime(null);
                transactionAndGatherDeliverRepository.save(secondTransactionAndGatherDeliver);

                break;
            case Constant.onDeliverTransactionToCustomer: case Constant.failedTransactionToCustomer:

                firstCustomerAndTransactionDeliver.setState(Constant.successfulTransactionStock);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                firstTransactionAndGatherDeliver.setState(Constant.successfulGatherStock);
                transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                gatherAndGatherDeliver.setState(Constant.successfulGatherStock);
                gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);

                secondTransactionAndGatherDeliver.setState(Constant.successfulGatherStock);
                transactionAndGatherDeliverRepository.save(secondTransactionAndGatherDeliver);

                secondCustomerAndTransactionDeliver.setReceiveTime(null);
                customerAndTransactionDeliverRepository.save(secondCustomerAndTransactionDeliver);

                break;
            default:
                firstCustomerAndTransactionDeliver.setState(Constant.successfulTransactionStock);
                customerAndTransactionDeliverRepository.save(firstCustomerAndTransactionDeliver);

                firstTransactionAndGatherDeliver.setState(Constant.successfulGatherStock);
                transactionAndGatherDeliverRepository.save(firstTransactionAndGatherDeliver);

                gatherAndGatherDeliver.setState(Constant.successfulGatherStock);
                gatherAndGatherDeliverRepository.save(gatherAndGatherDeliver);

                secondTransactionAndGatherDeliver.setState(Constant.successfulTransactionStock);
                transactionAndGatherDeliverRepository.save(secondTransactionAndGatherDeliver);

                customerAndTransactionDeliverRepository.save(secondCustomerAndTransactionDeliver);
                break;
        }

        return new ResponseEntity<>("Created by tool!", HttpStatus.OK);
    }
}
