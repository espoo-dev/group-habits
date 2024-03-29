require 'rails_helper'

describe 'api/v1/categories', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:user_categories) { [category, category2] }
    let!(:category) { create(:category, name: 'abc', user:) }
    let!(:category2) { create(:category, name: 'def', user:) }
    let!(:categories2) { create_list(:category, 1) }

    context 'when not filtering' do
      before { get api_v1_categories_path, headers: auth_headers, as: :json }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's categories" do
        expect(json_response.pluck('id')).to match_array(user_categories.pluck(:id))
      end
    end

    context 'when filtering' do
      before do
        get api_v1_categories_path, params: { name: 'bc' }, headers: auth_headers, as: :json
      end
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it "returns user's categories" do
        expect(json_response.pluck('id')).to match_array([category.id])
      end
    end
  end

  describe '#create' do
    let(:user) { create(:user) }

    before do
      post api_v1_categories_path, params: create_category_params, headers: auth_headers, as: :json
    end
    context 'when data is valid' do
      let(:create_category_params) { attributes_for(:category, user_id: nil) }

      it 'returns status 201 created' do
        expect(response).to be_created
      end

      it 'returns category' do
        expect(json_response['id']).to_not be_nil
        expect(json_response['name']).to eq(create_category_params[:name])
      end
    end

    context 'when data is not valid' do
      let(:create_category_params) { attributes_for(:category, name: nil, user_id: nil) }

      it 'returns status 400 bad_request' do
        expect(response).to be_bad_request
      end

      it 'returns category' do
        expect(json_response['error']).to eq("Validation failed: Name can't be blank")
      end
    end
  end

  describe '#destroy' do
    let(:user) { create(:user) }
    let!(:category) { create(:category, user:) }

    before { delete api_v1_category_path(category_id), headers: auth_headers, as: :json }

    describe 'when finds category' do
      let(:category_id) { category.id }
      it 'returns status 204 no_content' do
        expect(response).to be_no_content
      end

      it 'returns an empty hash' do
        expect(response.body).to eq('')
      end
    end

    describe 'when does not find category' do
      let(:category_id) { -1 }

      it 'returns status 404 not_found' do
        expect(response).to be_not_found
      end

      it 'returns proper error message' do
        expect(json_response['error']).to eq("Couldn't find Category with 'id'=-1")
      end
    end
  end

  describe '#update' do
    let(:user) { create(:user) }
    let!(:category) { create(:category, user:) }

    before do
      put api_v1_category_path(category.id), params: update_category_params, headers: auth_headers,
                                             as: :json
    end

    context 'when data is valid' do
      let(:update_category_params) { { name: 'new_name' } }
      it 'returns status 200 ok' do
        expect(response).to be_successful
      end

      it 'updates category name' do
        expect(category.reload.name).to eq('new_name')
      end
    end

    context 'when data is not valid' do
      let(:update_category_params) { { name: nil } }
      it 'returns status 400 bad_request' do
        expect(response).to be_bad_request
      end
    end
  end
end
