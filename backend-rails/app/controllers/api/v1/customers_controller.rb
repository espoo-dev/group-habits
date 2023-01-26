module Api
  module V1
    class CustomersController < Api::V1::ApiController
      def index
        customers = CustomersFinderService.new(user: current_user, index_params:).call
        render json: CustomerPresenter.payload_for_list(customers)
      end

      def create
        customer = CustomerCreatorService.new(user: current_user, create_customer_params:).call
        render json: CustomerPresenter.payload_for_item(customer), status: :created
      end

      private

      def index_params
        params.permit(:name, :customer_type)
      end

      def create_customer_params
        params.permit(:name, :document_number, :phone, :state_inscription, :customer_type)
      end
    end
  end
end
