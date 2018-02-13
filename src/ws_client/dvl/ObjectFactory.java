
package ws_client.dvl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws_client.dvl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckAdminId_QNAME = new QName("http://Server/", "checkAdminId");
    private final static QName _CreateRoomResponse_QNAME = new QName("http://Server/", "createRoomResponse");
    private final static QName _GetAvailableTimeSlotByRoom_QNAME = new QName("http://Server/", "getAvailableTimeSlotByRoom");
    private final static QName _ChangeReservation_QNAME = new QName("http://Server/", "changeReservation");
    private final static QName _CheckIDAdmin_QNAME = new QName("http://Server/", "checkIDAdmin");
    private final static QName _BookRoom_QNAME = new QName("http://Server/", "bookRoom");
    private final static QName _GetAvailableTimeSlotCount_QNAME = new QName("http://Server/", "getAvailableTimeSlotCount");
    private final static QName _ChangeReservationResponse_QNAME = new QName("http://Server/", "changeReservationResponse");
    private final static QName _CancelBooking_QNAME = new QName("http://Server/", "cancelBooking");
    private final static QName _CheckAdminIdResponse_QNAME = new QName("http://Server/", "checkAdminIdResponse");
    private final static QName _GetAvailableTimeSlotCountResponse_QNAME = new QName("http://Server/", "getAvailableTimeSlotCountResponse");
    private final static QName _InitServerResponse_QNAME = new QName("http://Server/", "initServerResponse");
    private final static QName _BookRoomResponse_QNAME = new QName("http://Server/", "bookRoomResponse");
    private final static QName _GetAvailableTimeSlotByRoomResponse_QNAME = new QName("http://Server/", "getAvailableTimeSlotByRoomResponse");
    private final static QName _InitServer_QNAME = new QName("http://Server/", "initServer");
    private final static QName _DeleteRoomResponse_QNAME = new QName("http://Server/", "deleteRoomResponse");
    private final static QName _RunResponse_QNAME = new QName("http://Server/", "runResponse");
    private final static QName _CheckIDAdminResponse_QNAME = new QName("http://Server/", "checkIDAdminResponse");
    private final static QName _CreateRoom_QNAME = new QName("http://Server/", "createRoom");
    private final static QName _DeleteRoom_QNAME = new QName("http://Server/", "deleteRoom");
    private final static QName _Run_QNAME = new QName("http://Server/", "run");
    private final static QName _CancelBookingResponse_QNAME = new QName("http://Server/", "cancelBookingResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws_client.dvl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookRoomResponse }
     * 
     */
    public BookRoomResponse createBookRoomResponse() {
        return new BookRoomResponse();
    }

    /**
     * Create an instance of {@link GetAvailableTimeSlotByRoomResponse }
     * 
     */
    public GetAvailableTimeSlotByRoomResponse createGetAvailableTimeSlotByRoomResponse() {
        return new GetAvailableTimeSlotByRoomResponse();
    }

    /**
     * Create an instance of {@link InitServerResponse }
     * 
     */
    public InitServerResponse createInitServerResponse() {
        return new InitServerResponse();
    }

    /**
     * Create an instance of {@link CheckAdminIdResponse }
     * 
     */
    public CheckAdminIdResponse createCheckAdminIdResponse() {
        return new CheckAdminIdResponse();
    }

    /**
     * Create an instance of {@link GetAvailableTimeSlotCountResponse }
     * 
     */
    public GetAvailableTimeSlotCountResponse createGetAvailableTimeSlotCountResponse() {
        return new GetAvailableTimeSlotCountResponse();
    }

    /**
     * Create an instance of {@link CancelBooking }
     * 
     */
    public CancelBooking createCancelBooking() {
        return new CancelBooking();
    }

    /**
     * Create an instance of {@link CancelBookingResponse }
     * 
     */
    public CancelBookingResponse createCancelBookingResponse() {
        return new CancelBookingResponse();
    }

    /**
     * Create an instance of {@link DeleteRoom }
     * 
     */
    public DeleteRoom createDeleteRoom() {
        return new DeleteRoom();
    }

    /**
     * Create an instance of {@link Run }
     * 
     */
    public Run createRun() {
        return new Run();
    }

    /**
     * Create an instance of {@link CheckIDAdminResponse }
     * 
     */
    public CheckIDAdminResponse createCheckIDAdminResponse() {
        return new CheckIDAdminResponse();
    }

    /**
     * Create an instance of {@link CreateRoom }
     * 
     */
    public CreateRoom createCreateRoom() {
        return new CreateRoom();
    }

    /**
     * Create an instance of {@link RunResponse }
     * 
     */
    public RunResponse createRunResponse() {
        return new RunResponse();
    }

    /**
     * Create an instance of {@link DeleteRoomResponse }
     * 
     */
    public DeleteRoomResponse createDeleteRoomResponse() {
        return new DeleteRoomResponse();
    }

    /**
     * Create an instance of {@link InitServer }
     * 
     */
    public InitServer createInitServer() {
        return new InitServer();
    }

    /**
     * Create an instance of {@link CheckIDAdmin }
     * 
     */
    public CheckIDAdmin createCheckIDAdmin() {
        return new CheckIDAdmin();
    }

    /**
     * Create an instance of {@link ChangeReservation }
     * 
     */
    public ChangeReservation createChangeReservation() {
        return new ChangeReservation();
    }

    /**
     * Create an instance of {@link GetAvailableTimeSlotByRoom }
     * 
     */
    public GetAvailableTimeSlotByRoom createGetAvailableTimeSlotByRoom() {
        return new GetAvailableTimeSlotByRoom();
    }

    /**
     * Create an instance of {@link CreateRoomResponse }
     * 
     */
    public CreateRoomResponse createCreateRoomResponse() {
        return new CreateRoomResponse();
    }

    /**
     * Create an instance of {@link CheckAdminId }
     * 
     */
    public CheckAdminId createCheckAdminId() {
        return new CheckAdminId();
    }

    /**
     * Create an instance of {@link ChangeReservationResponse }
     * 
     */
    public ChangeReservationResponse createChangeReservationResponse() {
        return new ChangeReservationResponse();
    }

    /**
     * Create an instance of {@link BookRoom }
     * 
     */
    public BookRoom createBookRoom() {
        return new BookRoom();
    }

    /**
     * Create an instance of {@link GetAvailableTimeSlotCount }
     * 
     */
    public GetAvailableTimeSlotCount createGetAvailableTimeSlotCount() {
        return new GetAvailableTimeSlotCount();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAdminId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "checkAdminId")
    public JAXBElement<CheckAdminId> createCheckAdminId(CheckAdminId value) {
        return new JAXBElement<CheckAdminId>(_CheckAdminId_QNAME, CheckAdminId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRoomResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "createRoomResponse")
    public JAXBElement<CreateRoomResponse> createCreateRoomResponse(CreateRoomResponse value) {
        return new JAXBElement<CreateRoomResponse>(_CreateRoomResponse_QNAME, CreateRoomResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableTimeSlotByRoom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "getAvailableTimeSlotByRoom")
    public JAXBElement<GetAvailableTimeSlotByRoom> createGetAvailableTimeSlotByRoom(GetAvailableTimeSlotByRoom value) {
        return new JAXBElement<GetAvailableTimeSlotByRoom>(_GetAvailableTimeSlotByRoom_QNAME, GetAvailableTimeSlotByRoom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "changeReservation")
    public JAXBElement<ChangeReservation> createChangeReservation(ChangeReservation value) {
        return new JAXBElement<ChangeReservation>(_ChangeReservation_QNAME, ChangeReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckIDAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "checkIDAdmin")
    public JAXBElement<CheckIDAdmin> createCheckIDAdmin(CheckIDAdmin value) {
        return new JAXBElement<CheckIDAdmin>(_CheckIDAdmin_QNAME, CheckIDAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookRoom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "bookRoom")
    public JAXBElement<BookRoom> createBookRoom(BookRoom value) {
        return new JAXBElement<BookRoom>(_BookRoom_QNAME, BookRoom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableTimeSlotCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "getAvailableTimeSlotCount")
    public JAXBElement<GetAvailableTimeSlotCount> createGetAvailableTimeSlotCount(GetAvailableTimeSlotCount value) {
        return new JAXBElement<GetAvailableTimeSlotCount>(_GetAvailableTimeSlotCount_QNAME, GetAvailableTimeSlotCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "changeReservationResponse")
    public JAXBElement<ChangeReservationResponse> createChangeReservationResponse(ChangeReservationResponse value) {
        return new JAXBElement<ChangeReservationResponse>(_ChangeReservationResponse_QNAME, ChangeReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelBooking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "cancelBooking")
    public JAXBElement<CancelBooking> createCancelBooking(CancelBooking value) {
        return new JAXBElement<CancelBooking>(_CancelBooking_QNAME, CancelBooking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAdminIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "checkAdminIdResponse")
    public JAXBElement<CheckAdminIdResponse> createCheckAdminIdResponse(CheckAdminIdResponse value) {
        return new JAXBElement<CheckAdminIdResponse>(_CheckAdminIdResponse_QNAME, CheckAdminIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableTimeSlotCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "getAvailableTimeSlotCountResponse")
    public JAXBElement<GetAvailableTimeSlotCountResponse> createGetAvailableTimeSlotCountResponse(GetAvailableTimeSlotCountResponse value) {
        return new JAXBElement<GetAvailableTimeSlotCountResponse>(_GetAvailableTimeSlotCountResponse_QNAME, GetAvailableTimeSlotCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "initServerResponse")
    public JAXBElement<InitServerResponse> createInitServerResponse(InitServerResponse value) {
        return new JAXBElement<InitServerResponse>(_InitServerResponse_QNAME, InitServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookRoomResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "bookRoomResponse")
    public JAXBElement<BookRoomResponse> createBookRoomResponse(BookRoomResponse value) {
        return new JAXBElement<BookRoomResponse>(_BookRoomResponse_QNAME, BookRoomResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableTimeSlotByRoomResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "getAvailableTimeSlotByRoomResponse")
    public JAXBElement<GetAvailableTimeSlotByRoomResponse> createGetAvailableTimeSlotByRoomResponse(GetAvailableTimeSlotByRoomResponse value) {
        return new JAXBElement<GetAvailableTimeSlotByRoomResponse>(_GetAvailableTimeSlotByRoomResponse_QNAME, GetAvailableTimeSlotByRoomResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "initServer")
    public JAXBElement<InitServer> createInitServer(InitServer value) {
        return new JAXBElement<InitServer>(_InitServer_QNAME, InitServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRoomResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "deleteRoomResponse")
    public JAXBElement<DeleteRoomResponse> createDeleteRoomResponse(DeleteRoomResponse value) {
        return new JAXBElement<DeleteRoomResponse>(_DeleteRoomResponse_QNAME, DeleteRoomResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "runResponse")
    public JAXBElement<RunResponse> createRunResponse(RunResponse value) {
        return new JAXBElement<RunResponse>(_RunResponse_QNAME, RunResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckIDAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "checkIDAdminResponse")
    public JAXBElement<CheckIDAdminResponse> createCheckIDAdminResponse(CheckIDAdminResponse value) {
        return new JAXBElement<CheckIDAdminResponse>(_CheckIDAdminResponse_QNAME, CheckIDAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRoom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "createRoom")
    public JAXBElement<CreateRoom> createCreateRoom(CreateRoom value) {
        return new JAXBElement<CreateRoom>(_CreateRoom_QNAME, CreateRoom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRoom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "deleteRoom")
    public JAXBElement<DeleteRoom> createDeleteRoom(DeleteRoom value) {
        return new JAXBElement<DeleteRoom>(_DeleteRoom_QNAME, DeleteRoom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Run }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "run")
    public JAXBElement<Run> createRun(Run value) {
        return new JAXBElement<Run>(_Run_QNAME, Run.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Server/", name = "cancelBookingResponse")
    public JAXBElement<CancelBookingResponse> createCancelBookingResponse(CancelBookingResponse value) {
        return new JAXBElement<CancelBookingResponse>(_CancelBookingResponse_QNAME, CancelBookingResponse.class, null, value);
    }

}
