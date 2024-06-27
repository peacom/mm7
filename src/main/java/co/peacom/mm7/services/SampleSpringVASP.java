package co.peacom.mm7.services;

import co.peacom.mm7.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SampleSpringVASP")
public class SampleSpringVASP implements VASP {

    private final MM7Context context;

    public SampleSpringVASP(MM7Context context) {
        this.context = context;
    }

    @Override
    public DeliverRsp deliver(DeliverReq deliverReq) throws MM7Error {
        System.out.println("deliver in VASP was called");

        return null;
    }

    @Override
    public MM7Context getContext() {
        return context;
    }

	@Override
	public DeliveryReportRsp deliveryReport(DeliveryReportReq deliveryReportReq) throws MM7Error {
		System.out.println("deliveryReport in VASP was called");

        return null;
	}

	@Override
	public ReadReplyRsp readReply(ReadReplyReq readReplyReq) throws MM7Error {
		System.out.println("readReplyReq in VASP was called");
		
		return null;
	}
}
