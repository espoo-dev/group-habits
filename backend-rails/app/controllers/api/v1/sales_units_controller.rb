module Api
  module V1
    class SalesUnitsController < Api::V1::ApiController
      def index
        sales_units = SalesUnits::SalesUnitsFinderService.new(user: current_user, params: {}).call
        render json: SalesUnitPresenter.payload_for_list(sales_units)
      end
    end
  end
end
